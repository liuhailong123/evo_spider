package cn.com.evo.integration.guangxi;import cn.com.evo.admin.manage.domain.entity.Province;import cn.com.evo.admin.manage.service.ProvinceService;import cn.com.evo.cms.domain.entity.cms.Content;import cn.com.evo.cms.domain.entity.cms.Picture;import cn.com.evo.cms.domain.entity.cms.Video;import cn.com.evo.cms.domain.entity.total.ContentOperation;import cn.com.evo.cms.service.cms.ContentService;import cn.com.evo.cms.service.cms.PictureService;import cn.com.evo.cms.service.cms.VideoService;import cn.com.evo.cms.service.total.ContentOperationService;import cn.com.evo.integration.common.utils.FileToMulFile;import cn.com.evo.integration.guangxi.content.xml.CreateXML;import cn.com.evo.integration.guangxi.utils.GxgdUtils;import cn.com.evo.provincial.service.AbstractProvincialServiceImpl;import com.frameworks.utils.FtpUtils;import com.google.common.collect.Lists;import org.apache.logging.log4j.LogManager;import org.apache.logging.log4j.Logger;import org.springframework.beans.BeanUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.web.multipart.MultipartFile;import javax.transaction.Transactional;import java.util.List;import java.util.Map;/** * @Description:广西广电内容服务类 * @author: lu.xin * @create: 2019-05-14 10:50 AM **/@Service@Transactionalpublic class GxgdContentServiceImpl extends AbstractProvincialServiceImpl {    protected Logger logger = LogManager.getLogger(this.getClass());    private static final String UPLOAD_SUCCESS = "true";    @Autowired    private ContentService contentService;    @Autowired    private VideoService videoService;    @Autowired    private PictureService pictureService;    @Autowired    private ProvinceService provinceService;    @Autowired    private ContentOperationService contentOperationService;    @Override    public Video getPlayURL(String accesstoken, List<Video> temps, String appId) {        Video video = new Video();        BeanUtils.copyProperties(temps.get(0), video);        video.setUrl(video.getId());        return video;    }    /**     * 上传文件至ftp目录     *     * @param fileLocalPath     * @param province     */    public void registFileToFtp(String fileLocalPath, Province province, boolean deleteFlag) {        try {            String command = "chmod 777 " + fileLocalPath;            Runtime.getRuntime().exec(command).waitFor();            String name = getFileName(fileLocalPath);            MultipartFile multipartFile = FileToMulFile.getMulFileByPath(fileLocalPath,                    name);            String path = "/" + name;            Map<String, String> map = FtpUtils.upload(province.getFtpUrl(), province.getFtpUser(),                    province.getFtpPassword(), Integer.valueOf(province.getFtpPort()), multipartFile, path);            String flag = map.get("flag");            if (deleteFlag) {                if (UPLOAD_SUCCESS.equals(flag)) {//                    String command1 = "rm -rf " + fileLocalPath;//                    Runtime.getRuntime().exec(command1).waitFor();                }            }        } catch (Exception e) {            throw new RuntimeException("上传资源至ftp服务器异常：" + e.getMessage(), e);        }    }    /**     * 上传文件至ftp目录     *     * @param province     */    @Override    public void registImage(Picture picture, Province province, boolean deleteFlag) {        String fileLocalPath = picture.getCloudPath();        try {            String command = "chmod 777 " + fileLocalPath;            Runtime.getRuntime().exec(command).waitFor();            String name = getFileName(fileLocalPath);            MultipartFile multipartFile = FileToMulFile.getMulFileByPath(fileLocalPath,                    name);            String path = "/" + name;            Map<String, String> map = FtpUtils.upload(province.getFtpUrl(), province.getFtpUser(),                    province.getFtpPassword(), Integer.valueOf(province.getFtpPort()), multipartFile, path);            String flag = map.get("flag");            if (deleteFlag) {                if (UPLOAD_SUCCESS.equals(flag)) {//                    String command1 = "rm -rf " + fileLocalPath;//                    Runtime.getRuntime().exec(command1).waitFor();                }            }        } catch (Exception e) {            throw new RuntimeException("上传资源至ftp服务器异常：" + e.getMessage(), e);        }    }    /**     * 电影首次注入     *     * @param contentId     * @param province     */    @Override    public void registMovie(String contentId, Province province) {        // 上传图片和视频文件至ftp服务器        moveMoviePictureAndVideoFile(contentId);        // 电影注入流程        movieProcess(contentId, province);    }    @Override    public void updateMovie(String contentId, Province province) {    }    @Override    public void deleteMovie(String contentId, Province province) {    }    /**     * 剧集首次注入     *     * @param contentId     * @param province     */    @Override    public void registSeries(String contentId, Province province) {        // 剧集获取内容对应子集内容列表        List<Content> contentChild = contentService.findByPIdOrderBySortAsc(contentId);        // 上传图片和视频文件至ftp服务器        moveSeriesPictureAndVideoFile(contentId, contentChild);        Content episode = contentService.findById(contentId);        List<Content> childs = contentService.findByPIdOrderBySortAsc(episode.getId());        for (Content child : childs) {            // 剧集逻辑            episodeProcess(episode.getId(), child.getId(), province);        }    }    @Override    public void updateSeries(String contentId, Province province) {    }    @Override    public void deleteSeries(String contentId, Province province) {    }    /**     * 剧集子集首次注入     *     * @param contentId     * @param province     */    @Override    public void registSeriesChild(String contentId, Province province) {        List<Content> contentChild = Lists.newArrayList();        Content child = contentService.findById(contentId);        contentChild.add(child);        Content episode = contentService.findById(child.getpId());        // 上传图片和视频文件至ftp服务器        moveSeriesPictureAndVideoFile(episode.getId(), contentChild);        // 剧集逻辑        episodeProcess(episode.getId(), child.getId(), province);    }    /**     * 获取文件名     *     * @param url 文件路径     * @return     */    private static String getFileName(String url) {        Integer index = url.lastIndexOf("/");        return url.substring(index + 1, url.length());    }    /**     * 上传电影相关图片文件和视频文件至ftp目录     *     * @param contentId     */    public void moveMoviePictureAndVideoFile(String contentId) {        try {            Province province = provinceService.getByEnable(1);            if (province == null) {                throw new RuntimeException("没有可用的省网配置!!!");            }            Content content = contentService.findById(contentId);            if (content == null) {                throw new RuntimeException("内容不存在!!!无法进行注入!!!");            }            // 获取内容对应图片对象list            List<Picture> pictures = pictureService.findByContentId(contentId);            // 获取内容相关视频对象list            List<Video> videos = videoService.findByContentId(contentId);            // 开始移动-图片文件            for (Picture picture : pictures) {                String localFile = picture.getCloudPath();                try {                    registFileToFtp(localFile, province, false);                } catch (Exception e) {                    logger.error("上传图片至ftp异常：" + e.getMessage());                }            }            // 开始移动-视频文件            for (Video video : videos) {                String localFile = video.getUrl();                try {                    registFileToFtp(localFile, province, false);                } catch (Exception e) {                    logger.error("上传视频至ftp异常：" + e.getMessage());                }            }        } catch (Exception e) {            throw new RuntimeException("上传内容相关图片和视频文件至FTP目录异常:" + e.getMessage(), e);        }    }    /**     * 上传剧集相关图片和视频文件至FTP服务器     *     * @param contentId     * @param children     */    public void moveSeriesPictureAndVideoFile(String contentId, List<Content> children) {        try {            Province province = provinceService.getByEnable(1);            if (province == null) {                throw new RuntimeException("没有可用的省网配置!!!");            }            Content content = contentService.findById(contentId);            if (content == null) {                throw new RuntimeException("内容不存在!!!无法进行注入!!!");            }            // 获取内容对应图片对象list            List<Picture> pictures = pictureService.findByContentId(contentId);            // 开始移动-图片文件            for (Picture picture : pictures) {                String localFile = picture.getCloudPath();                try {                    registFileToFtp(localFile, province, false);                } catch (Exception e) {                    logger.error("上传图片至ftp异常：" + e.getMessage());                }            }            for (Content child : children) {                // 获取内容相关视频对象list                List<Video> videos = videoService.findByContentId(child.getId());                // 开始移动-视频文件                for (Video video : videos) {                    String localFile = video.getUrl();                    try {                        registFileToFtp(localFile, province, false);                    } catch (Exception e) {                        logger.error("上传视频至ftp异常：" + e.getMessage());                    }                }            }        } catch (Exception e) {            throw new RuntimeException("上传内容相关图片和视频文件至FTP目录异常:" + e.getMessage(), e);        }    }    /**     * 电影注入逻辑     *     * @param contentId     * @param province     */    private void movieProcess(String contentId, Province province) {        String xmlStr = "";        try {            // 获取内容对象            Content content = contentService.findById(contentId);            // 创建子内容对象            Content child = new Content();            BeanUtils.copyProperties(content, child);            child.setId(child.getId() + "_1");            child.setSort(1);            // 获取内容相关视频对象list            Video video = new Video();            List<Video> videos = videoService.findByContentId(contentId);            if (videos.size() > 0) {                // 设置视频对象                video = videos.get(0);            }            // 获取内容对应海报            Picture hPicture = null;            Picture sPicture = null;            List<Picture> pictures = pictureService.findByContentId(contentId);            for (Picture picture : pictures) {                if (picture.getType() == 1) {                    hPicture = picture;                }                if (picture.getType() == 2) {                    sPicture = picture;                }            }            // 生成xml字符串            xmlStr = CreateXML.create(content, child, video, hPicture, sPicture);        } catch (Exception e) {            throw new RuntimeException("根据电影内容创建xml异常！！！" + e.getMessage(), e);        }        String xmlLocalPath = "";        try {            // 创建本地xml文件并上传            xmlLocalPath = GxgdUtils.createLoaclXML(contentId, xmlStr, province);            registFileToFtp(xmlLocalPath, province, false);        } catch (Exception e) {            throw new RuntimeException("创建本地xml文件异常" + e.getMessage(), e);        }        // TODO 未完成        try {            // 更新内容注入状态            Content content = contentService.findById(contentId);            content.setSynType(1);            contentService.update(content);            // 保存内容采集记录表            ContentOperation contentOperation = new ContentOperation();            contentOperation.setContent(content);//            contentOperation.setCorrelateId(correlateId);            contentOperation.setInfo(xmlLocalPath);            contentOperation.setStatus(1);            contentOperationService.save(contentOperation);        } catch (Exception e) {            throw new RuntimeException("调用局方接口：通知局方进行内容采集异常" + e.getMessage(), e);        }    }    /**     * 剧集逻辑     *     * @param contentId     * @param childId     * @param province     */    private void episodeProcess(String contentId, String childId, Province province) {        String xmlStr = "";        try {            // 获取内容对象            Content content = contentService.findById(contentId);            // 创建子内容对象            Content child = contentService.findById(childId);            // 获取内容相关视频对象list            Video video = new Video();            List<Video> videos = videoService.findByContentId(childId);            if (videos.size() > 0) {                // 设置视频对象                video = videos.get(0);            }            // 获取内容对应海报            Picture hPicture = null;            Picture sPicture = null;            List<Picture> pictures = pictureService.findByContentId(contentId);            for (Picture picture : pictures) {                if (picture.getType() == 1) {                    hPicture = picture;                }                if (picture.getType() == 2) {                    sPicture = picture;                }            }            // 生成xml字符串            xmlStr = CreateXML.create(content, child, video, hPicture, sPicture);        } catch (Exception e) {            throw new RuntimeException("根据剧集子集内容创建xml异常！！！" + e.getMessage(), e);        }        String xmlLocalPath = "";        try {            // 创建本地xml文件并上传            xmlLocalPath = GxgdUtils.createLoaclXML(childId, xmlStr, province);            registFileToFtp(xmlLocalPath, province, false);        } catch (Exception e) {            throw new RuntimeException("创建本地xml文件异常" + e.getMessage(), e);        }        // TODO 未完成        try {            // 更新内容注入状态            Content content = contentService.findById(childId);            content.setSynType(1);            contentService.update(content);            // 保存内容采集记录表            ContentOperation contentOperation = new ContentOperation();            contentOperation.setContent(content);//            contentOperation.setCorrelateId(correlateId);            contentOperation.setInfo(xmlLocalPath);            contentOperation.setStatus(1);            contentOperationService.save(contentOperation);        } catch (Exception e) {            throw new RuntimeException("调用局方接口：通知局方进行内容采集异常" + e.getMessage(), e);        }    }}