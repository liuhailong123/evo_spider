package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.enums.*;
import cn.com.evo.cms.repository.cms.ContentRepository;
import cn.com.evo.cms.repository.cms.IndexConfChildRepository;
import cn.com.evo.cms.service.cms.*;
import cn.com.evo.cms.utils.ChineseCharUtil;
import cn.com.evo.integration.xjdx.common.XjdxUtils;
import cn.com.evo.integration.xjdx.content.model.Movie;
import cn.com.evo.integration.xjdx.content.model.Program;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.utils.DateUtil;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Component
public class ContentServiceImpl extends AbstractBaseService<Content, String> implements ContentService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private IndexConfChildRepository indexConfChildRepository;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private VideoService videoService;

    @Override
    protected BaseRepository<Content, String> getBaseRepository() {
        return contentRepository;
    }

    @Override
    public void save(Content entity) {
        try {
            if (entity.getSort() == null || entity.getSort() == 0) {
                entity.setSort(1);
            }
            entity.setCode(getCode());
            setPinYin(entity);
            super.save(entity);

            // 判断总集数是否存在
            Integer sunNum = entity.getSumNum();
            if (ContentClassifyEnum.episode.getIndex() == entity.getClassify()
                    && sunNum != null
                    && sunNum > 0) {
                // 存在，则自动生成相应的子集对象
                for (int i = 1; i <= sunNum; i++) {
                    // 循环调用EpisodeChildSerivce
                    Content child = new Content();
                    child.setSort(i);
                    child.setpId(entity.getId());
                    child.setName(entity.getName() + "" + i);
                    child.setClassify(ContentClassifyEnum.episode_child.getIndex());
                    child.setEnable(1);
                    child.setClassifyTags(entity.getClassifyTags());
                    this.save(child);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("内容保存失败" + e.getMessage());
        }
    }

    @Override
    public void update(Content entity) {
        try {
            if (entity.getEnable() == 0) {
                checkContent(entity.getId());
            }

            if (entity.getSort() == null || entity.getSort() == 0) {
                entity.setSort(1);
            }
            setPinYin(entity);
            super.update(entity);
        } catch (Exception e) {
            throw new RuntimeException("内容保存失败" + e.getMessage());
        }
    }


    @Override
    public void save(Content entity, String columnId, int publishSort) {
        if (entity.getSort() == null || entity.getSort() == 0) {
            entity.setSort(1);
        }

        // 发布内容保存时，自动增加当前 所属栏目 标签（去重）
        String classifyTags = getClassifyTags(entity.getClassifyTags(), columnId);
        entity.setClassifyTags(classifyTags);
        this.save(entity);

        // 判断该内容是否关联栏目
        CatalogueRelation catalogueRelation = catalogueRelationService.getByAIdAndBIdAndType(columnId, entity.getId(),
                2);
        if (catalogueRelation == null) {
            catalogueRelation = new CatalogueRelation();
        }
        if (publishSort == 0) {
            publishSort = 1;
        }
        catalogueRelation.setSort(publishSort);
        catalogueRelation.setAId(columnId);
        catalogueRelation.setBId(entity.getId());
        catalogueRelation.setType(2);
        catalogueRelation.setPublish(entity.getEnable());
        catalogueRelation.setContentType(1);
        catalogueRelationService.save(catalogueRelation);

    }

    @Override
    public void update(Content entity, String contentVideo, String contentMusic, String contentImage,
                       String contentText, String columnId, int publishSort) {
        //增加内容停用时的校验
        if (entity.getEnable() == 0) {
            checkContent(entity.getId());
        }

        // 保存相关资源关系
        saveSoureRel(entity, contentVideo, contentImage, contentMusic, contentText);

        // 发布内容编辑时，自动增加当前 所属栏目 标签（去重）
        String classifyTags = getClassifyTags(entity.getClassifyTags(), columnId);
        entity.setClassifyTags(classifyTags);
        this.save(entity, columnId, publishSort);

    }

    @Override
    public void update(Content entity, String contentImage, String contentVideo) {
        //增加内容停用时的校验
        if (entity.getEnable() == 0) {
            checkContent(entity.getId());
        }

        // 保存相关资源关系
        saveSoureRel(entity, contentVideo, contentImage, null, null);

        super.update(entity);
    }


    @Override
    public void deleteById(String id) {
        // 判断内容是否挂载至栏目或者首页
        checkContent(id);

        List<Content> contents = contentRepository.findByPIdOrderBySortAsc(id);
        if (contents != null) {
            if (contents.size() > 0) {
                for (Content content : contents) {
                    sourceRelService.deleteByFId(content.getId());
                }
                super.deleteByEntities(contents);
            }
        }
        sourceRelService.deleteByFId(id);
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void dataMoveUpdate(String contentId, String oldContentId) {
        contentRepository.dataMoveUpdate(contentId, oldContentId);
    }


    @Override
    public Content getByNamePublish(String name) {
        return contentRepository.getByNamePublish(name);
    }

    @Override
    public Content getByName(String name) {
        return contentRepository.getByName(name);
    }

    @Override
    public List<Content> findByColumnId(String columnId) {
        List<Content> entitys = Lists.newArrayList();
        List<CatalogueRelation> relations = catalogueRelationService.findByAIdAndTypeAndIsPublishOrderBySortAsc(
                columnId, CatalogueRelationType.columnsContentRel.getIndex());
        for (CatalogueRelation catalogueRelation : relations) {
            if (catalogueRelation != null) {
                Content entity = this.findById(catalogueRelation.getBId());
                entitys.add(entity);
            }
        }
        return entitys;
    }

    @Override
    public List<Content> findByName(String name) {
        return contentRepository.findByName(name);
    }

    @Override
    public Long findByPIdTotal(String pId) {
        return contentRepository.findByPIdTotal(pId);
    }

    @Override
    public List<Content> findByPIdOrderBySortAsc(String contentId) {
        return contentRepository.findByPIdOrderBySortAsc(contentId);
    }

    @Override
    public List<Content> findByPIdAndClassifyOrderBySortAsc(String contentId, Integer classify) {
        return contentRepository.findByPIdAndClassifyAndEnableOrderBySortAsc(contentId, classify, 1);
    }

    @Override
    public List<Content> findByCode(String code) {
        return contentRepository.findByCode(code);
    }

    @Override
    public List<Content> findByNameAndClassify(String name, Integer classify) {
        return contentRepository.findByNameAndClassify(name, classify);
    }

    @Override
    public void importFile(MultipartFile[] files) {
        if (files == null) {
            throw new RuntimeException("没有文件");
        }
        for (MultipartFile mFile : files) {
            try {
                InputStream is = mFile.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new RuntimeException("导入文件的第一个sheet不存在");
                } else {
                    for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {// rowNum==0为表头
                        Row row = sheet.getRow(rowNum);
                        // 内容类型
                        Integer classify = Integer.valueOf(getValue(row.getCell(0)));
                        // 内容名称
                        String name = getValue(row.getCell(1));
                        //去空格
                        name = name.replace(" ", "");
                        // 年份
                        String year = getValue(row.getCell(2));
                        // 标签
                        String tag = getValue(row.getCell(3));
                        // 总集数
                        Integer sumNum = Integer.valueOf(getValue(row.getCell(4)));
                        // 评分
                        String grade = getValue(row.getCell(5));
                        // 时长
                        String runTime = getValue(row.getCell(6));
                        // 简介
                        String info = getValue(row.getCell(7));
                        // 演员标签
                        String actorTags = getValue(row.getCell(8));
                        // 导演标签
                        String directorTags = getValue(row.getCell(9));
                        String language = getValue(row.getCell(10));
                        List<Content> contents = this.findByNameAndClassify(name, classify);
                        if (contents == null || contents.size() == 0) {
                            Content content = new Content();
                            content.setEnable(1);
                            content.setRunTime(runTime);
                            content.setGrade(grade);
                            content.setSort(1);
                            content.setName(name);
                            content.setClassify(classify);
                            content.setYear(year);
                            content.setYearTags(year);
                            content.setClassifyTags(tag);
                            content.setSumNum(sumNum);
                            content.setInfo(info);
                            content.setActorTags(actorTags);
                            content.setDirectorTags(directorTags);
                            content.setLanguage(language);
                            this.save(content);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
        }
    }

    /**
     * 新疆电信片单解析
     * @param files
     */
    @Override
    public List<Program> xjdxImportFile(MultipartFile[] files) {
        if (files == null) {
            throw new RuntimeException("没有文件");
        }
        List<Program> programs = new ArrayList<>();
        for (MultipartFile mFile : files) {
            try {
                InputStream is = mFile.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new RuntimeException("导入文件的第一个sheet不存在");
                } else {
                    for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {// rowNum==0为表头
                        Row row = sheet.getRow(rowNum);

                        //名称
                        String name = String.valueOf(row.getCell(0));
                        System.out.println(name);
                        //编码
                        String code = String.valueOf(row.getCell(1));
                        System.out.println(code);
                        //别名
                        String reName = String.valueOf(row.getCell(2));
                        System.out.println(reName);
                        //搜索名
                        String shortName = String.valueOf(row.getCell(3));
                        System.out.println(shortName);
                        //供应商
                        String supplier = String.valueOf(row.getCell(4));
                        System.out.println(supplier);
                        //是否收费
                        String isCharge = String.valueOf(row.getCell(5));
                        System.out.println(isCharge);
                        //影片类型
                        String movieType = String.valueOf(row.getCell(6));
                        System.out.println(movieType);
                        //内容类型
                        String contentType = String.valueOf(row.getCell(7));
                        //影片编码
                        String movieCode = String.valueOf(row.getCell(8));
                        System.out.println(movieCode);
                        //影片url    ftp链接
                        String url = String.valueOf(row.getCell(9));
                        System.out.println(url);
                        //上映日期
                        Cell dateCell = row.getCell(10);
                        String showYear = "";
                        //判断是否为日期类型
                        if(0==dateCell.getCellType()) {
                            if (DateUtil.isCellDateFormatted(dateCell)) {
                                //用于转化为日期格式
                                Date d = dateCell.getDateCellValue();
                                DateFormat formater = new SimpleDateFormat("yyyyMMdd");
                                showYear = formater.format(d);
                            }
                        }
                        System.out.println(showYear);
                        //导演
                        String director = String.valueOf(row.getCell(11));
                        System.out.println(director);
                        //国家地区
                        String addr = String.valueOf(row.getCell(12));
                        System.out.println(addr);
                        //主演
                        String actor = String.valueOf(row.getCell(13));
                        System.out.println(actor);
                        //语言
                        String language = String.valueOf(row.getCell(14));
                        System.out.println(language);
                        //海报图片
                        String imgName = String.valueOf(row.getCell(15));
                        System.out.println(imgName);
                        //排序
                        String sort = String.valueOf(row.getCell(16));
                        Double sort1 = Double.parseDouble(sort);
                        Integer sort2 = sort1.intValue();
                        System.out.println(sort2);
                        //描述
                        String info = String.valueOf(row.getCell(17));
                        System.out.println(info);
                        //一级分类
                        String definitionFlag = String.valueOf(row.getCell(18));
                        System.out.println(definitionFlag);
                        //海报编码id
                        String picCode = String.valueOf(row.getCell(19));
                        System.out.println(picCode);
                        //海报地址url ftp
                        String picUrl = String.valueOf(row.getCell(20));
                        System.out.println(picUrl);
                        //海报地址url ftp
                        String pid = String.valueOf(row.getCell(21));
                        System.out.println(pid);

                        Program program = new Program("Program", code, "REGIST", code, name, reName, shortName, supplier
                                , isCharge, contentType, showYear, director, addr, actor, language, imgName, sort2+"", info, definitionFlag, picCode, picUrl);
                        program.setpId(pid);

                        Movie movie = new Movie(movieCode, movieType, url);
                        program.setMovie(movie);
                        programs.add(program);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
        }
        return programs;
    }

    @Override
    public Content findContentByNumber(String catalogueRelationId, Integer number) {
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(catalogueRelationId);
            String contentId = catalogueRelation.getBId();
            // 当前子集对象
            Content content = this.findById(contentId);
            if (content == null) {
                throw new RuntimeException("内容:" + contentId + "不存在!!!");
            }
            if (ContentClassifyEnum.movie.getIndex() == content.getClassify()) {
                // 电影
                return null;
            } else {
                Content child = contentRepository.getContentByIdAndSort(content.getId(), number);
                return child;
            }

        } catch (Exception e) {
            throw new RuntimeException("获取下一集内容对象异常" + e.getMessage(), e);
        }
    }

    /**
     * 设置拼音
     *
     * @param entity
     */
    @Override
    public void setPinYin(Content entity) throws PinyinException {
        if (StringUtils.isNotBlank(entity.getName())) {
            String name = entity.getName();
            // 去除中文中的特殊字符
            String nameTemp = ChineseCharUtil.removeSpecialChar(name);
            // 获取全拼
            String nameLongPinyin = PinyinHelper.convertToPinyinString(nameTemp, "", PinyinFormat.WITHOUT_TONE);
            // 获取简拼
            String nameShortPinyin = PinyinHelper.getShortPinyin(nameTemp);
            entity.setNameSpellLong(nameLongPinyin);
            entity.setNameSpellShort(nameShortPinyin);
        }
        if (StringUtils.isNotBlank(entity.getTitle())) {
            String title = entity.getTitle();
            // 去除中文中的特殊字符
            String titleTemp = ChineseCharUtil.removeSpecialChar(title);
            // 获取全拼
            String titleLongPinyin = PinyinHelper.convertToPinyinString(titleTemp, "", PinyinFormat.WITHOUT_TONE);
            // 获取简拼
            String titleShortPinyin = PinyinHelper.getShortPinyin(titleTemp);
            entity.setTitleSpellLong(titleLongPinyin);
            entity.setTitleSpellShort(titleShortPinyin);
        }
    }

    /**
     * 自动绑定内容与视频、内容与图片关系
     *
     * @param content
     */
    @Override
    public void autoBindVideoAndImageRel(Content content) {
        // 内容与图片关系
        List<Picture> pictures = pictureService.findByName(content.getName());
        for (Picture picture : pictures) {
            String imageId = picture.getId();
            SourceRel imageRel = sourceRelService.getSourceRel(content.getId(), SourceRelTypeEnum.contentRel.getIndex(),
                    imageId, SourceTypeEnum.image.getIndex(), BusinessTypeEnum.cover.getIndex());
            if (imageRel == null) {
                imageRel = new SourceRel();
                imageRel.setfId(content.getId());
                imageRel.setRelType(SourceRelTypeEnum.contentRel.getIndex());
                imageRel.setSourceId(imageId);
                imageRel.setSourcetype(SourceTypeEnum.image.getIndex());
                imageRel.setBusinessType(BusinessTypeEnum.cover.getIndex());
                sourceRelService.save(imageRel);
            }
        }

        // 内容与视频关系,分为电影与内容关系，电影与图片关系
        if (content.getClassify() == 2) {
            // 剧集总集
            // 根据content获取剧集子集contentChild
            List<Content> contentChildren = this.findByPIdOrderBySortAsc(content.getId());
            for (Content child : contentChildren) {
                // 直接通过子集名称获取
                List<Video> videos = videoService.findByName(child.getName());
                if (videos.size() == 0) {
                    String name = content.getName() + "000" + child.getSort();
                    videos = videoService.findLikeName(name);
                    if (videos.size() == 0) {
                        name = content.getName() + "00" + child.getSort();
                        videos = videoService.findLikeName(name);
                        if (videos.size() == 0) {
                            name = content.getName() + "0" + child.getSort();
                            videos = videoService.findLikeName(name);
                            if (videos.size() == 0) {
                                name = content.getName() + child.getSort();
                                videos = videoService.findLikeName(name);
                                if (videos.size() == 0) {
                                    name = content.getName() + "_" + child.getSort();
                                    videos = videoService.findLikeName(name);
                                    if (videos.size() == 0) {
                                        name = content.getName() + "第" + child.getSort();
                                        videos = videoService.findLikeName(name);
                                    }
                                }
                            }
                        }
                    }
                }

                for (Video video : videos) {
                    String videoId = video.getId();
                    SourceRel videoRel = sourceRelService.getSourceRel(child.getId(), SourceRelTypeEnum.contentRel.getIndex(),
                            videoId, SourceTypeEnum.video.getIndex());
                    if (videoRel == null) {
                        videoRel = new SourceRel();
                        videoRel.setfId(child.getId());
                        videoRel.setRelType(SourceRelTypeEnum.contentRel.getIndex());
                        videoRel.setSourceId(videoId);
                        videoRel.setSourcetype(SourceTypeEnum.video.getIndex());
                        sourceRelService.save(videoRel);
                        break;
                    }
                }
            }
        } else {
            // 电影
            List<Video> videos = videoService.findLikeName(content.getName());
            for (Video video : videos) {
                String videoId = video.getId();

                SourceRel videoRel = sourceRelService.getSourceRel(content.getId(), SourceRelTypeEnum.contentRel.getIndex(),
                        videoId, SourceTypeEnum.video.getIndex());
                if (videoRel == null) {
                    videoRel = new SourceRel();
                    videoRel.setfId(content.getId());
                    videoRel.setRelType(SourceRelTypeEnum.contentRel.getIndex());
                    videoRel.setSourceId(videoId);
                    videoRel.setSourcetype(SourceTypeEnum.video.getIndex());
                    sourceRelService.save(videoRel);
                    break;
                }
            }
        }
    }

    @Override
    public void refresh() {
        List<Content> all = findAll();
        for (Content content : all) {
            try {
                setPinYin(content);
            } catch (PinyinException e) {
                e.printStackTrace();
            }
            super.update(content);
        }
    }

    @Override
    public List<Content> findByPIdAndSynType(String pId, Integer synType) {
        return contentRepository.findByPIdAndSynType(pId, synType);
    }

    @Override
    public Content findByVideoId(String assetId) {
        return contentRepository.findByVideoId(assetId);
    }
    //------------------------------------------私有方法

    /**
     * 获取编码
     *
     * @return
     */
    private String getCode() {
        Double a = (Math.random() * 9 + 1) * 10000000;
        return a.intValue() + "";
    }

    private String getClassifyTags(String classifyTags, String columnId) {
        Column column = columnService.findById(columnId);
        if (!"".equals(classifyTags) && classifyTags != null) {
            if (classifyTags.indexOf(column.getName()) == -1) {
                // 不包含
                classifyTags += "," + column.getName();
            }
        } else {
            classifyTags = column.getName();
        }
        return classifyTags;
    }


    /**
     * 检测内容是否被使用
     *
     * @param contentId
     */
    private void checkContent(String contentId) {
        // 检测内容是否被挂载至栏目
        List<CatalogueRelation> list = catalogueRelationService.findByBId(contentId);
        if (list.size() > 0) {
            throw new RuntimeException(" 该内容已被发布至栏目！无法停用或删除");
        }

        // 检测内容是否被挂载至首页
        List<IndexConfChild> indexConfChildren = indexConfChildRepository.findByContentId(contentId);
        if (indexConfChildren.size() > 0) {
            throw new RuntimeException(" 该内容已被发布至首页！无法停用或删除");
        }
    }

    /**
     * 保存内容相关的视频、图片、音频、文本的关系
     *
     * @param entity
     * @param contentVideo
     * @param contentImage
     * @param contentMusic
     * @param contentText
     */
    private void saveSoureRel(Content entity, String contentVideo, String contentImage, String contentMusic, String contentText) {
        // video
        if (contentVideo != null && !"".equals(contentVideo)) {
            JSONArray ja = JSONArray.parseArray(contentVideo);
            for (int i = 0; i < ja.size(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                SourceRel sourceRel = sourceRelService.getSourceRel(entity.getId(), SourceRelTypeEnum.contentRel.getIndex(),
                        jo.getString("videoId"), SourceTypeEnum.video.getIndex());
                if (sourceRel == null) {
                    sourceRel = new SourceRel();
                    sourceRel.setfId(entity.getId());
                    sourceRel.setRelType(SourceRelTypeEnum.contentRel.getIndex());
                    sourceRel.setSourceId(jo.getString("videoId"));
                    sourceRel.setSourcetype(SourceTypeEnum.video.getIndex());
                    sourceRelService.save(sourceRel);
                }
            }
        }
        // picture
        if (contentImage != null && !"".equals(contentImage)) {
            JSONArray ja = JSONArray.parseArray(contentImage);
            for (int i = 0; i < ja.size(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                SourceRel sourceRel = sourceRelService.getSourceRel(entity.getId(), SourceRelTypeEnum.contentRel.getIndex(),
                        jo.getString("imageId"), SourceTypeEnum.image.getIndex(), jo.getInteger("businessType"));
                if (sourceRel == null) {
                    sourceRel = new SourceRel();
                    sourceRel.setfId(entity.getId());
                    sourceRel.setRelType(SourceRelTypeEnum.contentRel.getIndex());
                    sourceRel.setSourceId(jo.getString("imageId"));
                    sourceRel.setSourcetype(SourceTypeEnum.image.getIndex());
                    sourceRel.setBusinessType(jo.getInteger("businessType"));
                    sourceRelService.save(sourceRel);
                }
            }
        }
        // music
        if (contentMusic != null && !"".equals(contentMusic)) {
            // TODO
        }

        // text
        if (contentText != null && !"".equals(contentText)) {
            // TODO
        }
    }

    /**
     * 读取cell的值
     *
     * @param cell
     * @return
     */
    @SuppressWarnings("static-access")
    public String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            DecimalFormat df = new DecimalFormat("0");
            // 去除科学记数法
            return df.format(cell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }
}
