package cn.com.evo.cms.web.voService;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.enums.BusinessTypeEnum;
import cn.com.evo.cms.domain.enums.ContentTypeEnum;
import cn.com.evo.cms.domain.vo.cms.SourcePictureVo;
import cn.com.evo.cms.repository.cms.ContentRepository;
import cn.com.evo.cms.repository.cms.ContentVoRepository;
import cn.com.evo.cms.service.book.BookInfoService;
import cn.com.evo.cms.service.cms.*;
import cn.com.evo.cms.web.api.vo.EpisodeChildApiVo;
import cn.com.evo.cms.web.api.vo.PictureApiVo;
import cn.com.evo.cms.web.api.vo.ShowContentApiVo;
import cn.com.evo.integration.xjTvos.content.XJWebserviceSDK;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.frameworks.core.web.search.SearchFilter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class ContentVoService extends AbstractBaseService<Content, String> {

    private ContentRepository contentRepository;

    @Override
    protected BaseRepository<Content, String> getBaseRepository() {
        return contentRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ContentVoRepository contentVoRepository;
    @Autowired
    private ContentService contentService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private LliveBroadcastService liveService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private ActiveService activeService;

    @Autowired
    private PictureVoService pictureVoService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;


    public List<EpisodeChildApiVo> findByPIdByPage(String episodeId, Integer pageNum, Integer pageSize) {
        List<EpisodeChildApiVo> episodeChildApiVos = Lists.newArrayList();
        // 初始化分页对象
        Pager pager = new Pager();
        pager.setPageNumber(pageNum);
        pager.setPageSize(pageSize);
        pager.setSortName("sort");
        pager.setSortOrder("asc");
        // 初始化条件对象
        List<SearchFilter> filters = Lists.newArrayList();
        filters.add(new SearchFilter("classify", SearchFilter.Operator.EQ, 3));
        filters.add(new SearchFilter("pId", SearchFilter.Operator.EQ, episodeId));
        filters.add(new SearchFilter("enable", SearchFilter.Operator.EQ, 1));
        Specification<Content> specification = DynamicSpecifications.bySearchFilter(Content.class, filters);
        List<Content> childs = contentService.findByCondition(specification, pager);
        for (Content child : childs) {
            EpisodeChildApiVo vo = new EpisodeChildApiVo(child.getId(), child.getName(), child.getSort());
            episodeChildApiVos.add(vo);
        }
        return episodeChildApiVos;
    }

    public List<EpisodeChildApiVo> findByPId(String episodeId) {
        List<EpisodeChildApiVo> episodeChildApiVos = Lists.newArrayList();
        List<Content> childs = contentService.findByPIdAndClassifyOrderBySortAsc(episodeId, 3);
        for (Content child : childs) {
            EpisodeChildApiVo vo = new EpisodeChildApiVo(child.getId(), child.getName(), child.getSort());
            episodeChildApiVos.add(vo);
        }
        return episodeChildApiVos;
    }

    /**
     * 获取推荐内容，基于mysql全文检索算法
     *
     * @param content
     * @param pageNum
     * @param pageSize
     * @param appId
     * @return
     */
    public List<ShowContentApiVo> getRecommend(Content content, Integer pageNum, Integer pageSize, String appId) {
        List<ShowContentApiVo> apiVos = Lists.newArrayList();
        //拿到推荐内容（分页或者不分页）
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.relId as id,a.id as contentId,a.name,a.classify,a.score FROM (")
            .append(" SELECT c.id,r.id as relId,c.NAME,c.classify,")
            .append(" MATCH (c.name,c.YEAR,c.classifyTags,c.actorTags,c.directorTags,c.yearTags,c.areaTags) AGAINST (?1 IN boolean MODE) AS score")
            .append(" FROM cms_catalogue_relation r,cms_column co,cms_content c WHERE r.aId=co.id AND r.bId=c.id AND ")
            .append(" MATCH (c.name,c.YEAR,c.classifyTags,c.actorTags,c.directorTags,c.yearTags,c.areaTags) AGAINST (?2 IN boolean MODE)")
            .append(" AND c.ENABLE=1 AND c.classify !=3 AND co.columnCode LIKE ?3 AND r.type=2 AND r.publish=1 GROUP BY c.id LIMIT ?4,?5) a ORDER BY score DESC ");

        String classifyTags = content.getClassifyTags();
        if (StringUtils.isNotBlank(classifyTags)) {
            String classifyTag = classifyTags.split(",")[0];

            Column column = columnService.findById(appId);

            Integer start = (pageNum - 1) * pageSize;

            SQLQuery sqlQuery = entityManager.createNativeQuery(sql.toString())
                    .setParameter(1, "+" + classifyTag + " -" + content.getName().replaceAll(" ", ""))
                    .setParameter(2, "+" + classifyTag + " -" + content.getName().replaceAll(" ", ""))
                    .setParameter(3, column.getColumnCode() + "%")
                    .setParameter(4, start)
                    .setParameter(5, pageSize)
                    .unwrap(SQLQuery.class);

            // 返回接口映射为map
            List temps = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            List<String> ids = Lists.newArrayList();
            for (Object o : temps) {
                Map row = (Map) o;
                String id = String.valueOf(row.get("id"));
                ShowContentApiVo vo = new ShowContentApiVo(id,
                        String.valueOf(row.get("name")), Integer.valueOf(String.valueOf(row.get("classify"))));
                vo.setContentId(row.get("contentId").toString());
                ids.add(row.get("contentId").toString());
                apiVos.add(vo);
            }

            // 根据内容id获取海报数据
            List<SourcePictureVo> sourcePictureVos = pictureVoService.findPictureByContentIds(ids, BusinessTypeEnum.cover.getIndex());
            // 根据内容id分组
            Map<String, List<SourcePictureVo>> groupBy = sourcePictureVos.stream().collect(Collectors.groupingBy(SourcePictureVo::getSourceId));

            //获取当前省网资源服务器配置
            Province provice = provinceService.getByEnable(1);
            // 循环写数据
            for (ShowContentApiVo apiVo : apiVos) {
                List<PictureApiVo> lvos = Lists.newArrayList();

                // 根据内容id获取对应海报list
                List<SourcePictureVo> vos = groupBy.get(apiVo.getContentId());
                if (vos != null) {
                    for (SourcePictureVo temp : vos) {
                        PictureApiVo vo = new PictureApiVo(provice.getImageHost(), temp.getUrl(), temp.getType());
                        lvos.add(vo);
                    }
                }
                apiVo.setPictures(lvos);
            }
        }
        return apiVos;
    }

    public Long getRecommendTotal(Content content, String appId) {
        StringBuilder sql = new StringBuilder();
        Long total = null;
        sql.append("SELECT count(a.id)as count FROM (")
                .append(" SELECT c.id,c.NAME,c.classify,")
                .append(" MATCH (c.name,c.YEAR,c.classifyTags,c.actorTags,c.directorTags,c.yearTags,c.areaTags) AGAINST (?1 IN boolean MODE) AS score")
                .append(" FROM cms_catalogue_relation r,cms_column co,cms_content c WHERE r.aId=co.id AND r.bId=c.id AND ")
                .append(" MATCH (c.name,c.YEAR,c.classifyTags,c.actorTags,c.directorTags,c.yearTags,c.areaTags) AGAINST (?2 IN boolean MODE)")
                .append(" AND c.ENABLE=1 AND c.classify !=3 AND co.columnCode LIKE ?3 AND r.type=2 AND r.publish=1 GROUP BY c.id) a");

        String classifyTags = content.getClassifyTags();
        if (StringUtils.isNotBlank(classifyTags)) {
            String classifyTag = classifyTags.split(",")[0];

            Column column = columnService.findById(appId);


            SQLQuery sqlQuery = entityManager.createNativeQuery(sql.toString())
                    .setParameter(1, "+" + classifyTag + " -" + content.getName().replaceAll(" ", ""))
                    .setParameter(2, "+" + classifyTag + " -" + content.getName().replaceAll(" ", ""))
                    .setParameter(3, column.getColumnCode() + "%")
                    .unwrap(SQLQuery.class);

            // 返回接口映射为map
            List temps = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            for (Object o : temps) {
                Map row = (Map) o;
                total = Long.valueOf(String.valueOf(row.get("count")));
            }
        }
        return total;
    }

    /**
     * 根据关键词模糊查询
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param appId
     * @return
     */
    public List<ShowContentApiVo> searchByKeyword(String keyword, Integer pageNum, Integer pageSize, String appId) {
        Integer start = (pageNum - 1) * pageSize;
        List<ShowContentApiVo> vos = Lists.newArrayList();
        Map<String, ShowContentApiVo> map = new HashMap<>();
        //根据内容标签推荐内容信息及海报
        Column column = columnService.findById(appId);
        List<Object[]> objects = contentVoRepository.searchByKeywordByLike(keyword, start, pageSize, column.getColumnCode());
        //数据解析成子集对象map
        if (objects != null) {
            if (objects.size() > 0) {
                map = getMapByObjects(objects);
            }
        }
        //遍历map转换为List
        for (ShowContentApiVo vo : map.values()) {
            vos.add(vo);
        }

        return vos;
    }

    /**
     * 根据关键词模糊查询 获取总数
     *
     * @param keyword
     * @param appId
     * @return
     */
    public Long searchByKeywordTotal(String keyword, String appId) {
        Long count = 0L;
        Column column = columnService.findById(appId);
        count = contentVoRepository.searchByKeywordTotal("%" + keyword + "%", column.getColumnCode() + "%");
        return count;
    }

    /**
     * 获取播放最多内容列表
     *
     * @param appId
     * @param pageSize
     * @param pageNum
     * @return
     */
    public List<ShowContentApiVo> findBylikeLook(String appId, Integer pageSize, Integer pageNum) {
        Integer start = (pageNum - 1) * pageSize;
        List<ShowContentApiVo> vos = Lists.newArrayList();
        Map<String, ShowContentApiVo> map = new HashMap<>();
        //根据内容标签推荐内容信息及海报
        List<Object[]> objects = contentVoRepository.findBylikeLook(appId, start, pageSize, 1);
        //数据解析成子集对象map
        if (objects != null) {
            if (objects.size() > 0) {
                map = getMapByObjects(objects);
            }
        }
        //遍历map转换为List
        for (ShowContentApiVo vo : map.values()) {
            vos.add(vo);
        }

        return vos;
    }

    public Long findBylikeLookTotal(String appId) {
        Long total = 0L;
        total = contentVoRepository.findBylikeLookTotal(appId, 1);
        return total;
    }


    /**
     * 目录内容对象list转化为接口内容对象list
     *
     * @param list         内容关系对象list
     * @param businessType 需要获取的图片的业务类型
     * @return
     */
    public List<ShowContentApiVo> catalogueRelationsToShowContentApiVos(List<CatalogueRelation> list, Integer businessType) {
        List<String> ids = Lists.newArrayList();
        for (CatalogueRelation catalogueRelation : list) {
            ids.add(catalogueRelation.getBId());
        }
        // 根据内容ids获取图片list
        List<SourcePictureVo> sourcePictures = pictureVoService.findPictureByContentIds(ids, businessType);

        // 根据内容id分组
        Map<String, List<SourcePictureVo>> groupBy = sourcePictures.stream().collect(Collectors.groupingBy(SourcePictureVo::getSourceId));

        // 获取当前省网资源服务器配置
        Province provice = provinceService.getByEnable(1);

        // 处理海报对象
        List<ShowContentApiVo> apiVos = Lists.newArrayList();
        for (CatalogueRelation catalogueRelation : list) {
            //获取内容对应海报list
            List<SourcePictureVo> temps = groupBy.get(catalogueRelation.getBId());
            List<PictureApiVo> pictures = Lists.newArrayList();
            if (temps != null) {
                for (SourcePictureVo temp : temps) {
                    PictureApiVo vo = new PictureApiVo(provice.getImageHost(), temp.getUrl(), temp.getType());
                    pictures.add(vo);
                }
            }
            ShowContentApiVo showContentApiVo = getShowContentApiVo(catalogueRelation.getContentType(), catalogueRelation.getBId(), catalogueRelation, pictures);
            /**
             * id重新赋值
             * 2019年04月08日10:22:212
             * lu.xin
             */
            showContentApiVo.setId(catalogueRelation.getId());

            if (showContentApiVo != null) {
                apiVos.add(showContentApiVo);
            }

            // 是否热门
            showContentApiVo.setIsHot(catalogueRelation.getIsHot());

            // 业务类型为1：普通；2：推荐
            if (catalogueRelation.getBusinessType() == 1) {
                showContentApiVo.setIsRecommend(0);
            } else {
                showContentApiVo.setIsRecommend(1);
            }

            // 当前集数
            Long nowCount = contentService.findByPIdTotal(catalogueRelation.getBId());
            if (nowCount != null) {
                showContentApiVo.setNowEpisodeCount(nowCount.intValue());
            } else {
                showContentApiVo.setNowEpisodeCount(1);
            }
        }
        return apiVos;
    }

    /**
     * 获取 接口内容vo对象
     *
     * @param contentType
     * @param contentId
     * @param pictures
     * @return
     */
    public ShowContentApiVo getShowContentApiVo(Integer contentType, String contentId, CatalogueRelation catalogueRelation, List<PictureApiVo> pictures) {
        ShowContentApiVo contentApiVo = null;
        ContentTypeEnum contentTypeEnum = ContentTypeEnum.val(contentType);
        switch (contentTypeEnum) {
            case movie:
                Content movie = contentService.findById(contentId);
                contentApiVo = new ShowContentApiVo(movie, pictures, catalogueRelation);
                break;
            case episode:
                Content episode = contentService.findById(contentId);
                contentApiVo = new ShowContentApiVo(episode, pictures, catalogueRelation);
                break;
            case live:
                LliveBroadcast live = liveService.findById(contentId);
                contentApiVo = new ShowContentApiVo(live, pictures, catalogueRelation);
                break;
            case active:
                Active active = activeService.findById(contentId);
                contentApiVo = new ShowContentApiVo(active, pictures, catalogueRelation);
                break;
            case book:
                BookInfo bookInfo = bookInfoService.findById(contentId);
                contentApiVo = new ShowContentApiVo(bookInfo, pictures, catalogueRelation);
                break;
            case column:
                Column column = columnService.findById(contentId);
                contentApiVo = new ShowContentApiVo(column, pictures, catalogueRelation);
                break;
            default:
                break;
        }
        return contentApiVo;
    }

    /**
     * 获取随机数量的推荐内容(视频)
     *
     * @param contentId 内容id
     * @param appId     应用id
     * @param dataSize  获取数据条数
     * @return
     */
    public List<ShowContentApiVo> getRecommendRandom(String contentId, String appId, Integer dataSize) {
        List<ShowContentApiVo> apiVos = Lists.newArrayList();
        try {
            // 获取栏目编码
            Column column = columnService.findById(appId);
            String columnCode = column.getColumnCode();

            // 获取内容标签
            Content content = contentService.findById(contentId);
            String tags = tranClassifyTags(content.getClassifyTags());

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT c.id,r.id as relId,c.name,c.classify FROM cms_content c,cms_catalogue_relation r,cms_column co")
                    .append(" where r.aId=co.id AND r.bId=c.id")
                    .append(" and c.classifyTags REGEXP concat_ws(\"|\"," + tags + ")")
                    .append(" and c.classify != 3 and c.enable = 1 and r.publish = 1")
                    .append(" and co.columnCode LIKE ?1 group by c.id")
                    .append(" ORDER BY RAND() LIMIT ?2");
            System.out.println(sql.toString());

            SQLQuery sqlQuery = entityManager.createNativeQuery(sql.toString())
                    .setParameter(1, columnCode + "%")
                    .setParameter(2, dataSize)
                    .unwrap(SQLQuery.class);

            // 返回接口映射为map
            List temps = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            List<String> ids = Lists.newArrayList();
            for (Object o : temps) {
                Map row = (Map) o;
                String cId = String.valueOf(row.get("id"));
                String id = String.valueOf(row.get("relId"));
                ShowContentApiVo vo = new ShowContentApiVo(id, String.valueOf(row.get("name")), Integer.valueOf(String.valueOf(row.get("classify"))));
                vo.setId(id);
                vo.setContentId(cId);

                ids.add(cId);
                apiVos.add(vo);
            }

            // 根据内容id获取海报数据
            List<SourcePictureVo> sourcePictureVos = pictureVoService.findPictureByContentIds(ids, BusinessTypeEnum.cover.getIndex());
            // 根据内容id分组
            Map<String, List<SourcePictureVo>> groupBy = sourcePictureVos.stream().collect(Collectors.groupingBy(SourcePictureVo::getSourceId));

            //获取当前省网资源服务器配置
            Province provice = provinceService.getByEnable(1);
            // 循环写数据
            for (ShowContentApiVo apiVo : apiVos) {
                List<PictureApiVo> lvos = Lists.newArrayList();

                // 根据内容id获取对应海报list
                List<SourcePictureVo> vos = groupBy.get(apiVo.getContentId());
                if (vos != null) {
                    for (SourcePictureVo temp : vos) {
                        PictureApiVo vo = new PictureApiVo(provice.getImageHost(), temp.getUrl(), temp.getType());
                        lvos.add(vo);
                    }
                }
                apiVo.setPictures(lvos);
            }
        } catch (Exception e) {
            throw new RuntimeException("获取随机数量的推荐内容（视频）异常:" + e.getMessage(), e);
        }
        return apiVos;
    }

    /**
     * 转换内容分类标签
     *
     * @param classifyTags 内容分类标签
     * @return 转换为mysql查询中使用的格式
     */
    private static String tranClassifyTags(String classifyTags) {
        if (StringUtils.isBlank(classifyTags)) {
            throw new RuntimeException("内容标签未录入");
        }
        String[] temps = classifyTags.split(",");
        String result = "";
        for (String temp : temps) {
            result += "\"" + temp + "\"" + ",";
        }
        return result.substring(0, result.length() - 1);
    }


    private Map<String, ShowContentApiVo> getMapByObjects(List<Object[]> objects) {
        //获取当前省网资源服务器配置
        Province provice = provinceService.getByEnable(1);
        Map<String, ShowContentApiVo> map = new HashMap<>();
        for (Object[] object : objects) {
            if (map.containsKey(object[0])) {//是否存在
                ShowContentApiVo vo = map.get(object[0]);
                List<PictureApiVo> pictures = vo.getPictures();
                if (object[3] != null && object[4] != null) {
                    PictureApiVo picture = new PictureApiVo(provice.getImageHost(), String.valueOf(object[3]), Integer.valueOf(object[4] + ""));
                    pictures.add(picture);
                }
                vo.setPictures(pictures);
                map.put(object[0] + "", vo);
            } else {
                ShowContentApiVo vo = new ShowContentApiVo(String.valueOf(object[0]), String.valueOf(object[1]), Integer.valueOf(object[2] + ""));
                List<PictureApiVo> pictures = Lists.newArrayList();
                if (object[3] != null && object[4] != null) {
                    PictureApiVo picture = new PictureApiVo(provice.getImageHost(), String.valueOf(object[3]), Integer.valueOf(object[4] + ""));
                    pictures.add(picture);
                }
                vo.setPictures(pictures);
                map.put(object[0] + "", vo);
            }
        }
        return map;
    }

    //调用局方鉴权接口获取首页轮播播放串
    public String getLiveUrlByAccessToken(String liveBroadcastId, String accesstoken) {
        LliveBroadcast lliveBroadcast = liveService.findById(liveBroadcastId);
        CatalogueRelation catalogueRelation = catalogueRelationService.findById(lliveBroadcast.getContentId());
        List<Content> contents = contentService.findByPIdOrderBySortAsc(catalogueRelation.getbId());
        List<Video> videos = videoService.findByContentId(contents.get(0).getId());
        return videos.get(0).getUrl();
//        return XJWebserviceSDK.getVideoAuthorityInfo(accesstoken, videos.get(0).getUrl());
    }
}
