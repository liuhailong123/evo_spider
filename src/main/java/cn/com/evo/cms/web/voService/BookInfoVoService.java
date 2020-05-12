package cn.com.evo.cms.web.voService;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.enums.BusinessTypeEnum;
import cn.com.evo.cms.domain.vo.cms.SourcePictureVo;
import cn.com.evo.cms.service.book.BookInfoService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.web.api.vo.PictureApiVo;
import cn.com.evo.cms.web.api.vo.ShowContentApiVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookInfoVoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private ColumnService columnService;

    @Autowired
    private PictureVoService pictureVoService;
    @Autowired
    private ProvinceService provinceService;

    public List<ShowContentApiVo> getRecommend(String bookId, Integer pageNum, Integer pageSize, String appId) {

        List<ShowContentApiVo> apiVos = Lists.newArrayList();
        //拿到推荐内容（分页或者不分页）
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.relId as id,a.id as contentId,a.name,a.score FROM ( ")
                .append(" SELECT c.id,r.id as relId,c.NAME,")
                .append(" MATCH (c.name,c.smallClassify,c.info, c.columnsNames,c.tags)AGAINST(?1 IN boolean MODE )  AS score")
                .append(" FROM cms_catalogue_relation r,cms_column co,book_info c WHERE r.aId=co.id AND r.bId=c.id AND  ")
                .append(" MATCH (c.name,c.smallClassify,c.info, c.columnsNames,c.tags)AGAINST(?2 IN boolean MODE ) ")
                .append(" AND co.columnCode LIKE ?3 AND r.type=2 AND r.publish=1 group by c.id LIMIT ?4,?5) a ORDER BY score DESC  ");
        BookInfo bookInfo = bookInfoService.findById(bookId);
        if (bookInfo != null) {
            String classifyTags = bookInfo.getSmallClassify();
            if (StringUtils.isNotBlank(classifyTags)) {
                String classifyTag = classifyTags.split(",")[0];

                Column column = columnService.findById(appId);
                if (column != null) {
                    Integer start = (pageNum - 1) * pageSize;

                    String param1 = "+" + classifyTag + " -" + bookInfo.getName().replaceAll(" ", "");
                    SQLQuery sqlQuery = entityManager.createNativeQuery(sql.toString())
                            .setParameter(1, param1)
                            .setParameter(2, param1)
                            .setParameter(3, column.getColumnCode() + "%")
                            .setParameter(4, start)
                            .setParameter(5, pageSize)
                            .unwrap(SQLQuery.class);

                    // 返回接口映射为map
                    List temps = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
                    if (temps.size() > 0) {
                        List<String> ids = Lists.newArrayList();
                        for (Object o : temps) {
                            Map row = (Map) o;
                            String id = String.valueOf(row.get("id"));
                            String contentId = String.valueOf(row.get("contentId"));
                            ShowContentApiVo vo = new ShowContentApiVo(contentId,
                                    String.valueOf(row.get("name")), 5);
                            vo.setContentId(contentId);
                            vo.setId(id);
                            ids.add(contentId);
                            apiVos.add(vo);
                        }

                        // 根据内容id获取海报数据
                        List<SourcePictureVo> sourcePictureVos = pictureVoService.findPictureByContentIds(ids, BusinessTypeEnum.cover.getIndex());
                        if (sourcePictureVos.size() > 0) {
                            // 根据内容id分组
                            Map<String, List<SourcePictureVo>> groupBy = sourcePictureVos.stream().collect(Collectors.groupingBy(SourcePictureVo::getSourceId));

                            // 循环写数据
                            for (ShowContentApiVo apiVo : apiVos) {
                                List<PictureApiVo> lvos = Lists.newArrayList();
                                //获取当前省网资源服务器配置
                                Province provice = provinceService.getByEnable(1);
                                // 根据内容id获取对应海报list
                                List<SourcePictureVo> vos = groupBy.get(apiVo.getContentId());
                                if (vos != null && vos.size() > 0) {
                                    for (SourcePictureVo temp : vos) {
                                        PictureApiVo vo = new PictureApiVo(provice.getImageHost(), temp.getUrl(), temp.getType());
                                        lvos.add(vo);
                                    }
                                }
                                apiVo.setPictures(lvos);
                            }
                        }
                    }
                }
            }
        }
        return apiVos;
    }


    public Long getRecommendTotal(String id, String appId) {
        StringBuilder sql = new StringBuilder();
        Long total = 0L;
        sql.append("SELECT count(a.relId)as count FROM ( ")
                .append(" SELECT c.id,r.id as relId,c.NAME,")
                .append(" MATCH (c.name,c.smallClassify,c.info, c.columnsNames,c.tags)AGAINST(?1 IN boolean MODE )  AS score")
                .append(" FROM cms_catalogue_relation r,cms_column co,book_info c WHERE r.aId=co.id AND r.bId=c.id AND  ")
                .append(" MATCH (c.name,c.smallClassify,c.info, c.columnsNames,c.tags)AGAINST(?2 IN boolean MODE ) ")
                .append(" AND co.columnCode LIKE ?3 AND r.type=2 AND r.publish=1 group by c.id) a ");

        BookInfo bookInfo = bookInfoService.findById(id);
        if (bookInfo != null) {
            String classifyTags = bookInfo.getSmallClassify();
            if (StringUtils.isNotBlank(classifyTags)) {
                String classifyTag = classifyTags.split(",")[0];
                Column column = columnService.findById(appId);

                String param1 = "+" + classifyTag + " -" + bookInfo.getName().replaceAll(" ", "");
                SQLQuery sqlQuery = entityManager.createNativeQuery(sql.toString())
                        .setParameter(1, param1)
                        .setParameter(2, param1)
                        .setParameter(3, column.getColumnCode() + "%")
                        .unwrap(SQLQuery.class);

                // 返回接口映射为map
                List temps = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
                for (Object o : temps) {
                    Map row = (Map) o;
                    total = Long.valueOf(String.valueOf(row.get("count")));
                }
            }
        }
        return total;
    }


    /**
     * 获取随机数量的推荐内容(视频)
     *
     * @param contentId 内容id
     * @param appId     应用id
     * @param dataSize  获取数据条数
     * @return
     */
    public List<ShowContentApiVo> getBookRecommendRandom(String contentId, String appId, Integer dataSize) {
        List<ShowContentApiVo> apiVos = Lists.newArrayList();
        try {
            // 获取栏目编码
            Column column = columnService.findById(appId);
            String columnCode = column.getColumnCode();

            // 获取内容标签
            BookInfo bookInfo = bookInfoService.findById(contentId);
            String tags = tranClassifyTags(bookInfo.getSmallClassify());

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT c.id as contentId,r.id as relId,c.name,'5'as smallClassify FROM book_info c,cms_catalogue_relation r,cms_column co")
                    .append(" where r.aId=co.id AND r.bId=c.id")
                    .append(" and c.smallClassify REGEXP concat_ws(\"|\"," + tags + ")")
                    .append(" and r.type=2 AND r.publish=1")
                    .append(" and co.columnCode LIKE ?1 group by c.id")
                    .append(" ORDER BY RAND() LIMIT ?2");

            SQLQuery sqlQuery = entityManager.createNativeQuery(sql.toString())
                    .setParameter(1, columnCode + "%")
                    .setParameter(2, dataSize)
                    .unwrap(SQLQuery.class);

            // 返回接口映射为map
            List temps = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            List<String> ids = Lists.newArrayList();
            for (Object o : temps) {
                Map row = (Map) o;
                String id = String.valueOf(row.get("relId"));
                String cId = String.valueOf(row.get("contentId"));
                ShowContentApiVo vo = new ShowContentApiVo(id, String.valueOf(row.get("name")), Integer.valueOf(String.valueOf(row.get("smallClassify"))));
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
    public static String tranClassifyTags(String classifyTags) {
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
}
