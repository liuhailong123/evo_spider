package cn.com.evo.integration.nxsp;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.domain.entity.total.ContentOperation;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.VideoService;
import cn.com.evo.cms.service.total.ContentOperationService;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.common.enums.ActionEnum;
import cn.com.evo.integration.common.utils.BeanToXml;
import cn.com.evo.integration.common.utils.FileToMulFile;
import cn.com.evo.integration.nxsp.common.NxConstantEnum;
import cn.com.evo.integration.nxsp.common.NxUtils;
import cn.com.evo.integration.nxsp.content.NxWebserviceSDK;
import cn.com.evo.integration.nxsp.content.ws.*;
import cn.com.evo.integration.nxsp.content.xml.enums.ContentAssetEnum;
import cn.com.evo.integration.nxsp.content.xml.model.*;
import cn.com.evo.provincial.service.AbstractProvincialServiceImpl;
import com.frameworks.utils.DateUtil;
import com.frameworks.utils.FtpUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author rf
 * @date 2019/5/13
 */
@Service
@Transactional
public class NxContentServiceImpl extends AbstractProvincialServiceImpl {
    protected Logger logger = LogManager.getLogger(this.getClass());
    private static final String UPLOAD_SUCCESS = "true";
    @Autowired
    private ContentService contentService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ContentOperationService contentOperationService;
    @Autowired
    private NxAuthServiceImpl nxAuthService;


    /**
     * 电影首次注入
     *
     * @param contentId
     * @param province
     */
    @Override
    public void registMovie(String contentId, Province province) {
        // 移动文件至指定目录
//        moveMoviePictureAndVideoFile(contentId);
        // 电影注入流程
        movieProcess(contentId, province, ActionEnum.REGIST.getValue());
    }

    /**
     * 更改电影注入
     * @param contentId
     * @param province
     */
    @Override
    public void updateMovie(String contentId, Province province) {
        movieProcess(contentId, province, ActionEnum.UPDATE.getValue());
    }

    /**
     * 取消电影注入
     * @param contentId
     * @param province
     */
    @Override
    public void deleteMovie(String contentId, Province province) {
        movieProcess(contentId, province, ActionEnum.DELETE.getValue());
    }

    /**
     * 剧集首次注入
     * @param contentId
     * @param province
     */
    @Override
    public void registSeries(String contentId, Province province) {
        Content episode = contentService.findById(contentId);

        // 剧集获取内容对应子集内容列表
        List<Content> contentChild = contentService.findByPIdOrderBySortAsc(contentId);

        // 剧集注入流程
        seriesProcess(episode, contentChild, province, ActionEnum.REGIST.getValue());

    }

    @Override
    public void registSeriesChild(String contentId, Province province) {
        Content child = contentService.findById(contentId);
        Content content = contentService.findById(child.getpId());
        List<Content> childs = new ArrayList<>();
        childs.add(child);
        // 剧集注入流程
        seriesProcess(content, childs, province, ActionEnum.REGIST.getValue());
    }

    /**
     * 剧集修改注入
     *
     * @param contentId
     * @param province
     */
    @Override
    public void updateSeries(String contentId, Province province) {
        Content episode = contentService.findById(contentId);
        // 剧集获取内容对应子集内容列表
        List<Content> contentChild = contentService.findByPIdOrderBySortAsc(contentId);

        // 剧集注入流程
        seriesProcess(episode, contentChild, province, ActionEnum.REGIST.getValue());
    }

    /**
     * 剧集取消注入
     *
     * @param contentId
     * @param province
     */
    @Override
    public void deleteSeries(String contentId, Province province) {
        Content episode = contentService.findById(contentId);
        // 剧集获取内容对应子集内容列表
        List<Content> contentChild = contentService.findByPIdOrderBySortAsc(contentId);

        // 剧集注入流程
        seriesProcess(episode, contentChild, province, ActionEnum.DELETE.getValue());
    }

    /**
     * 剧集注入流程
     * @param episode
     * @param child
     * @param province
     * @param actionEnum 状态类标识,判断新增修改删除
     */
    private void seriesProcess(Content episode, List<Content> child, Province province, String actionEnum) {
        String xmlStr = "";
        String xmlUrl = "";
        try {
            if (child == null) {
                throw new RuntimeException("内容不存在!!!无法进行注入!!!");
            }
            // 获取内容对应图片对象list
            List<Picture> pictures = pictureService.findByContentId(episode.getId());

            // 转换
            ADI2 adi2 = convertADI2(episode, child, pictures, province, actionEnum);

            // 创建xml文件
            xmlStr = BeanToXml.beanToXml(adi2, false);
        } catch (Exception e) {
            throw new RuntimeException("根据剧集内容创建xml异常！！！" + e.getMessage(), e);
        }

        try {
            xmlUrl = NxUtils.createLoaclXML(episode.getId(), xmlStr, province);

        } catch (Exception e) {
            throw new RuntimeException("创建本地xml文件异常" + e.getMessage(), e);
        }

        try {
            String xmlFtpUrl = registFileToFtp(xmlUrl, province);
            String ftpPath = NxUtils.createFtpUrl(province);
            // 调用局方接口：通知局方进行内容采集
            String notify = ConstantFactory.map.get(NxConstantEnum.ingestAssest_notify.getKey());
            String bitRandomNum = NxUtils.createSixBitRandomNum();
            String sequence = "qdd" + bitRandomNum;
            IngestAssetResponse response = NxWebserviceSDK.callNXIngestAsset(ftpPath, xmlFtpUrl, notify, sequence);
            if(!response.getResultCode().equals(0)){
                throw new RuntimeException("宁夏媒资注入失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            }
            // 更新内容注入状态
            Content temp = contentService.findById(episode.getId());
            temp.setSynType(1);
            contentService.update(temp);
            for (Content content : child) {
                content.setSynType(1);
                contentService.update(content);
            }
            // 保存内容采集记录表
            ContentOperation contentOperation = new ContentOperation();
            contentOperation.setContent(episode);
            contentOperation.setCorrelateId(sequence);
            contentOperation.setInfo(xmlFtpUrl);
            contentOperation.setStatus(1);
            contentOperationService.save(contentOperation);
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口：通知局方进行内容采集异常" + e.getMessage(), e);
        }

    }

    /**
     * 上传文件至ftp目录
     *
     * @param fileLocalPath
     * @param province
     */
    public String registFileToFtp(String fileLocalPath, Province province) {
        try {
            String command = "chmod 777 " + fileLocalPath;
            Runtime.getRuntime().exec(command).waitFor();

            String name = getFileName(fileLocalPath);
            MultipartFile multipartFile = FileToMulFile.getMulFileByPath(fileLocalPath, name);
            String path = ConstantFactory.map.get(NxConstantEnum.ftp_xml_path.getKey()) + name;
            //上传
            Map<String, String> map = FtpUtils.upload(province.getFtpUrl(), province.getFtpUser(),
                    province.getFtpPassword(), Integer.valueOf(province.getFtpPort()), multipartFile, path);

            String flag = map.get("flag");
            if (UPLOAD_SUCCESS.equals(flag)) {
                String command1 = "rm -rf " + fileLocalPath;
                Runtime.getRuntime().exec(command1).waitFor();
                // xml的ftp访问路径
                String ftpPath = "ftp://" + province.getFtpUser() +
                        ":" + province.getFtpPassword() +
                        "@" + province.getFtpUrl() + ":" + province.getFtpPort();
                return name;
            } else {
                return "500";
            }
        } catch (Exception e) {
            throw new RuntimeException("上传资源至ftp服务器异常：" + e.getMessage(), e);
        }
    }

    /**
     * 获取文件名
     *
     * @param url 文件路径
     * @return
     */
    private static String getFileName(String url) {
        Integer index = url.lastIndexOf("/");
        return url.substring(index + 1, url.length());
    }

    /**
     * 电影注入流程
     * @param contentId
     * @param province
     * @param actionEnum  判断新增修改删除
     */
    private void movieProcess(String contentId, Province province, String actionEnum) {
        String xmlStr = "";
        String xmlUrl = "";
        try {
            Content content = contentService.findById(contentId);
            if (content == null) {
                throw new RuntimeException("内容不存在!!!无法进行注入!!!");
            }
            // 获取内容对应图片对象list
            List<Picture> pictures = pictureService.findByContentId(contentId);
            // 电影则内容总集数为1
            content.setSumNum(1);
            // 电影直接通过内容对象构造子内容对象
            List<Content> contentChild = Lists.newArrayList();
            content.setSort(1);
            contentChild.add(content);
            // 转换
            ADI2 adi2 = convertADI2(content, contentChild, pictures, province, actionEnum);
            // 创建xml文件
            xmlStr = BeanToXml.beanToXml(adi2, false);

        } catch (Exception e) {
            throw new RuntimeException("根据电影内容创建xml异常！！！" + e.getMessage(), e);
        }

        try {
            xmlUrl = NxUtils.createLoaclXML(contentId, xmlStr, province);

        } catch (Exception e) {
            throw new RuntimeException("创建本地xml文件异常" + e.getMessage(), e);
        }
        /**
         * 1、调用局方接口通知采集
         * 2、更新内容注入情况
         * 3、保存内容采集信息
         * 4、异常拦截 通知局方采集异常
         */
        try {
            String xmlFtpUrl = registFileToFtp(xmlUrl, province);
            String ftpUrl = NxUtils.createFtpUrl(province);
            // 调用局方接口：通知局方进行内容采集
            String notify = ConstantFactory.map.get(NxConstantEnum.ingestAssest_notify.getKey());
            NxWebserviceSDK.callNXIngestAsset(ftpUrl, xmlFtpUrl, notify, contentId);

            // 更新内容注入状态
            Content content = contentService.findById(contentId);
            content.setSynType(1);
            contentService.update(content);

            // 保存内容采集记录表
            ContentOperation contentOperation = new ContentOperation();
            contentOperation.setContent(content);
            contentOperation.setCorrelateId(contentId);
            contentOperation.setInfo(xmlFtpUrl);
            contentOperation.setStatus(1);
            contentOperationService.save(contentOperation);
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口：通知局方进行内容采集异常" + e.getMessage(), e);
        }
    }

    /**
     * 数据处理转换
     * 将 内容对象，子内容对象，海报对象 转换为底层接口需要的报文
     *
     * @param content  内容对象
     * @param children 子内容对象
     * @param pictures 海报对象
     * @param province 省网配置信息
     * @return
     */
    private ADI2 convertADI2(Content content,
                             List<Content> children,
                             List<Picture> pictures,
                             Province province,
                             String actionEnum) {
        if(StringUtils.isBlank(content.getId()) && StringUtils.isBlank(actionEnum)){
            throw new RuntimeException("请求注入失败：缺少主内容id或动作标识");
        }
        String ftpPath = "ftp://" + province.getFtpUser() + ":" +
                province.getFtpPassword() + "@" + province.getFtpHost() + ":" + province.getFtpPort();
        String providerId = ConstantFactory.map.get(NxConstantEnum.ProviderID.getKey());
        ADI2 adi2 = new ADI2();
        List<GroupAsset> groupAssets = new ArrayList<>();
        //AssociateContent
        List<AssociateContent> associateContents = new ArrayList<>();
        //List<AssociateGroup>
        List<AssociateGroup> associateGroups = new ArrayList<>();
        //创建metaDataAsset内容信息类
        List<MetadataAsset> metadataAssets = new ArrayList<>();
        //内容实体类
        List<ContentAsset> contentAssets = new ArrayList<>();
        //设置时间类
        AssetLifetime assetLifetime = new AssetLifetime();
        assetLifetime.setStartDateTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        assetLifetime.setEndDateTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        String sixBitRandomNum = NxUtils.createSixBitRandomNum();
        //剧头资产
        GroupAsset group = new GroupAsset();
        group.setType("VODRelease");
        group.setProduct("VOD");
        VODRelease groupRelease = new VODRelease();
        groupRelease.setProviderID(providerId);
        groupRelease.setProviderType("2");
        groupRelease.setAssetID(content.getId());
        groupRelease.setUpdateNum("");
        groupRelease.setGroupAsset("Y");
        groupRelease.setSerialNo(sixBitRandomNum);
        groupRelease.setAssetLifetime(assetLifetime);
        group.setVodRelease(groupRelease);
        groupAssets.add(group);
        //剧头title
        MetadataAsset groupMetadataAsset = new MetadataAsset();
        Title groupTitle = new Title();
        groupTitle.setAssetID(content.getId());
        groupTitle.setProviderID(providerId);
        groupTitle.setAssetLifetime(assetLifetime);
        groupTitle.setTitleFull(content.getName());
        //通过剧集数判断是电影还是电视剧
        if(content.getClassify()==1){
            groupTitle.setShowType("Movie");
        } else if(content.getClassify() == 2){
            groupTitle.setShowType("Series");
        }
        groupTitle.setSummaryMedium(content.getInfo());
        groupTitle.setSummaryShort(content.getInfo());
        groupTitle.setDirector(new Director(content.getDirectorTags()));
        groupTitle.setIsAdvertise("0");
        groupTitle.setStatus("1");
        groupTitle.setYear(content.getYear());
        groupMetadataAsset.setGroupProviderID(providerId);
        groupMetadataAsset.setType("Title");
        groupMetadataAsset.setProduct("VOD");
        groupMetadataAsset.setGroupAssetID(content.getId());
        groupMetadataAsset.setTitle(groupTitle);
        metadataAssets.add(groupMetadataAsset);
        //设置Copyright版权信息(其中版权开始时间和结束时间为必填项)
        MetadataAsset copyrightAsset = new MetadataAsset();
        Copyright copyright = new Copyright();
        AssetLifetime copyRightTime = new AssetLifetime();
        copyRightTime.setStartDateTime(DateUtil.thisYear());
        copyRightTime.setEndDateTime(DateUtil.thisYearThreeEnd());
        copyright.setAssetLifetime(copyRightTime);
        copyrightAsset.setGroupProviderID(providerId);
        copyrightAsset.setType("Copyright");
        copyrightAsset.setProduct("VOD");
        copyrightAsset.setGroupAssetID(content.getId());
        copyrightAsset.setCopyright(copyright);
        metadataAssets.add(copyrightAsset);

        //设置图片类信息并绑定剧头
        for (Picture picture : pictures) {
            if(StringUtils.isBlank(picture.getId()) && StringUtils.isBlank(picture.getFileName()) && StringUtils.isBlank(picture.getSize()) ){
                throw new RuntimeException("请求注入失败：缺少图片名称或图片大小信息");
            }
            ContentAsset contentAsset = new ContentAsset();
            contentAsset.setType(ContentAssetEnum.Image.getValue());
            contentAsset.setFileName(picture.getFileName());
            contentAsset.setFileSize(picture.getSize());
            contentAsset.setmD5CheckSum("");
            contentAsset.setOriginal_Asset_ID(picture.getId());
            Image image = new Image();
            image.setAssetID(picture.getId());
            image.setFileName(picture.getFileName());
            image.setFileSize(picture.getSize());
            image.setmD5CheckSum("");
            String ftpImgPath = ftpPath + "/" + ConstantFactory.map.get(NxConstantEnum.ftp_image_path.getKey())
                    + picture.getId() + "." + picture.getExt();
            image.setTransferContentURL(ftpImgPath);//这里是该上传文件ftp绝对路径
            image.setImageEncodingProfile("jpg/jpg");
            image.setAssetLifetime(assetLifetime);
            image.setColorType("RGB");
            if(StringUtils.isBlank(picture.getResolution())){
                String[] split = picture.getResolution().split("\\\\*");
                image.setImagePixels(new ImagePixels(split[0], split[1]));
            } else {
                image.setImagePixels(new ImagePixels("600", "400"));
            }
            image.setCaption(picture.getFileName());
            image.setFileType("3");
            image.setServiceType("12");
            image.setUsage("6");
            image.setMimeType("25");
            contentAsset.setImage(image);
            contentAssets.add(contentAsset);
            //设置元素映射关系
            AssociateContent associateContent = new AssociateContent();
            associateContent.setType("Image");
            associateContent.setEffectiveDate("");
            associateContent.setGroupProviderID(providerId);
            associateContent.setGroupAssetID(content.getId());
            associateContent.setAssetID(picture.getId());
            associateContent.setProviderID(providerId);
            associateContents.add(associateContent);
        }

        //创建子集GroupAsset
        for (Content child : children) {
            if(StringUtils.isBlank(child.getId())){
                throw new RuntimeException("请求注入失败：缺少内容id或动作标识");
            }
            //子集group
            GroupAsset groupAsset = new GroupAsset();
            groupAsset.setType("VODRelease");
            groupAsset.setProduct("VOD");
            VODRelease vodRelease = new VODRelease();
            vodRelease.setProviderID(providerId);
            vodRelease.setProviderType("2");
            vodRelease.setAssetID(child.getId());
            vodRelease.setUpdateNum("");
            vodRelease.setGroupAsset("Y");
            vodRelease.setSerialNo(sixBitRandomNum);
            vodRelease.setAssetLifetime(assetLifetime);
            groupAsset.setVodRelease(vodRelease);
            groupAssets.add(groupAsset);
            //绑定剧头group
            AssociateGroup associate = new AssociateGroup();
            associate.setEffectiveDate("");
            associate.setGroupProviderID(providerId);
            associate.setProviderID(providerId);
            associate.setAssetID(vodRelease.getAssetID());
            associate.setSourceGroupAssetID(vodRelease.getAssetID());
            associate.setTargetGroupAssetID(groupRelease.getAssetID());
            associateGroups.add(associate);

            if(StringUtils.isBlank(child.getName())){
                throw new RuntimeException("请求注入失败：缺少内容名称或介绍信息");
            }
            MetadataAsset titleAsset = new MetadataAsset();
            Title title = new Title();
            title.setAssetID(vodRelease.getAssetID());
            title.setProviderID(providerId);
            title.setAssetLifetime(assetLifetime);
            title.setTitleFull(child.getName());
            //通过剧集数判断是电影还是电视剧
            if(content.getClassify()==1){
                title.setShowType("Movie");
            } else if(content.getClassify() == 2){
                title.setShowType("Series");
            }
            title.setSummaryMedium(content.getInfo());
            title.setSummaryShort(content.getInfo());
            title.setDirector(new Director(content.getDirectorTags()));
            title.setIsAdvertise("0");
            title.setEpisodeId(child.getSort() + "");
            title.setStatus("1");
            title.setYear(content.getYear());
            titleAsset.setGroupProviderID(providerId);
            titleAsset.setType("Title");
            titleAsset.setProduct("VOD");
            titleAsset.setGroupAssetID(child.getId());
            titleAsset.setTitle(title);
            titleAsset.setGroupProviderID(providerId);
            metadataAssets.add(titleAsset);
            //视频信息
            List<Video> videos = videoService.findByContentId(child.getId());
            if(videos.size() > 0){
                for (Video videoContent : videos) {
                    if(StringUtils.isBlank(videoContent.getTime()) && StringUtils.isBlank(videoContent.getBitrate()) && StringUtils.isBlank(videoContent.getSize()) ){
                        throw new RuntimeException("请求注入失败：缺少视频码率/播放时长/视频大小信息");
                    }
                    ContentAsset contentAsset = new ContentAsset();
                    contentAsset.setType(ContentAssetEnum.Video.getValue());
                    contentAsset.setFileName(child.getName());
                    contentAsset.setFileSize(videoContent.getSize());
                    contentAsset.setmD5CheckSum("");
                    contentAsset.setOriginal_Asset_ID(videoContent.getId());
                    cn.com.evo.integration.nxsp.content.xml.model.Video video = new cn.com.evo.integration.nxsp.content.xml.model.Video();
                    video.setProviderID(providerId);
                    video.setAssetID(videoContent.getId());
                    video.setUpdateNum("1");
                    video.setFileName(child.getName());
                    video.setFileSize(videoContent.getSize());
                    video.setmD5CheckSum("");
                    //ftp路径

                    String ftpVideoPath = ftpPath + "/" + ConstantFactory.map.get(NxConstantEnum.ftp_video_path.getKey())
                            + videoContent.getId() + "." + videoContent.getExt();
                    video.setTransferContentURL(ftpVideoPath);//该视频的FTP绝对路径
                    video.setHdFlag("0");
                    video.setAssetLifetime(assetLifetime);
                    video.setDuration(videoContent.getTime());
                    video.setBitrate(videoContent.getBitrate());
                    video.setNumberofframes("");
                    video.setEncodingProfile("video/H264");
                    if(StringUtils.isNotBlank(videoContent.getResolution())){
                        if(videoContent.getResolution().contains("\\\\*")){
                            String[] split = videoContent.getResolution().split("\\\\*");
                            video.setFrameHeight(split[1]);
                            video.setFrameWidth(split[0]);
                        } else if(videoContent.getResolution().contains("x")){
                            String[] split = videoContent.getResolution().split("x");
                            video.setFrameHeight(split[1]);
                            video.setFrameWidth(split[0]);
                        }
                    }else {
                        video.setFrameHeight("3840");
                        video.setFrameWidth("2160");
                    }
                    video.setAspectRatio(videoContent.getResolution());
                    video.setFrameRate("30");
                    video.setFileType("1");
                    video.setUsage("1");
                    video.setIsFinished("1");
                    video.setServiceType("12");
                    video.setMimeType("43");
                    video.setAssetLifetime(assetLifetime);
                    contentAsset.setVideo(video);
                    contentAssets.add(contentAsset);
                    //设置元素映射关系
                    AssociateContent associateContent = new AssociateContent();
                    associateContent.setType("Video");
                    associateContent.setEffectiveDate("");
                    associateContent.setGroupProviderID(providerId);
                    associateContent.setGroupAssetID(child.getId());
                    associateContent.setAssetID(video.getAssetID());
                    associateContent.setProviderID(providerId);
                    associateContents.add(associateContent);
                }
            }
        }

        if ("REGIST".equals(actionEnum)){
            adi2.setOpenGroupAssets(groupAssets);
            adi2.setAddMetadataAsset(metadataAssets);
            adi2.setAcceptContentAsset(contentAssets);
            adi2.setAssociateContents(associateContents);
            adi2.setAssociateGroups(associateGroups);
            return adi2;
        } else if ("UPDATE".equals(actionEnum)){
            adi2.setReplaceGroupAsset(groupAssets);
            adi2.setReplaceMetadataAsset(metadataAssets);
            adi2.setReplaceContentAsset(contentAssets);
            adi2.setAssociateContents(associateContents);
            adi2.setAssociateGroups(associateGroups);
        } else if ("DELETE".equals(actionEnum)){

        } else {
            throw new RuntimeException("暂时不支持该操作!!!");
        }
        return null;
    }

    /**
     * 上传资源到指定ftp目录
     * @param contentId
     */
    public void moveMoviePictureAndVideoFile(String contentId) {
        try {
            Content content = contentService.findById(contentId);
            if (content == null) {
                throw new RuntimeException("内容不存在!!!无法进行注入!!!");
            }
            // 获取内容对应图片对象list
            List<Picture> pictures = pictureService.findByContentId(contentId);

            // 获取内容相关视频对象list
            List<Video> videos = videoService.findByContentId(contentId);

            // 开始移动-图片文件
            for (Picture picture : pictures) {
                String localFile = picture.getCloudPath();
                try {
                    File temp = new File(localFile);
                    NxUtils.moveFile(localFile, ConstantFactory.map.get(NxConstantEnum.ftp_image_path.getKey()) + temp.getName());
                }catch ( Exception e){
                }
            }

            // 开始移动-视频文件
            for (Video video : videos) {
                String localFile = video.getUrl();
                try {
                    File temp = new File(localFile);
                    NxUtils.moveFile(localFile, ConstantFactory.map.get(NxConstantEnum.ftp_video_path.getKey()) + temp.getName());
                }catch (Exception e){
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("移动内容相关图片和视频文件至指定目录异常:" + e.getMessage(), e);
        }
    }

    private Content copyContent(Content episode, Content child) {
        Content temp = new Content();
        temp.setId(child.getId());
        temp.setName(child.getName());
        temp.setGrade(episode.getGrade());
        temp.setTitle(episode.getTitle());
        temp.setYear(episode.getYear());
        temp.setInfo(episode.getInfo());
        temp.setRunTime(child.getRunTime());
        temp.setClassify(episode.getClassify());
        temp.setClassifyTags(episode.getClassifyTags());
        temp.setYearTags(episode.getYearTags());
        temp.setAreaTags(episode.getAreaTags());
        temp.setActorTags(episode.getActorTags());
        temp.setDirectorTags(episode.getDirectorTags());
        temp.setSumNum(episode.getSumNum());
        temp.setSort(child.getSort());
        temp.setLanguage(episode.getLanguage());
        return temp;
    }


    /**
     * 上传图片到ftp
     * @param picture
     * @param province
     */
    @Override
    public void registImage(Picture picture, Province province, boolean deleteFlag) {
        try {
            String command = "chmod 777 " + picture.getCloudPath();
            Runtime.getRuntime().exec(command).waitFor();

            MultipartFile multipartFile = FileToMulFile.getMulFileByPath(picture.getCloudPath(), picture.getFileName());
            // 匹配省网逻辑
            String name = picture.getFileName();
            String path = "/pub/image/" + name;
            Map<String, String> map = FtpUtils.upload(province.getFtpUrl(), province.getFtpUser(),
                    province.getFtpPassword(), Integer.valueOf(province.getFtpPort()), multipartFile, path);
            String flag = map.get("flag");
            if (UPLOAD_SUCCESS.equals(flag)) {
//                String command1 = "rm -rf " + picture.getCloudPath();
//                Runtime.getRuntime().exec(command1).waitFor();
            }
        } catch (Exception e) {
            throw new RuntimeException("上传资源至ftp服务器异常：" + e.getMessage(), e);
        }
    }

    /**
     * 上传视频到ftp
     * @param video
     * @param province
     */
    @Override
    public void registVideo(Video video, Province province) {
        try {
            String command = "chmod 777 " + video.getUrl();
            Runtime.getRuntime().exec(command).waitFor();

            MultipartFile multipartFile = FileToMulFile.getMulFileByPath(video.getUrl(), video.getId()+ "." + video.getExt());
            // 匹配省网逻辑
            String name = video.getId();
            String path = "/pub/video/" + name+ "." + video.getExt();
            Map<String, String> map = FtpUtils.upload(province.getFtpUrl(), province.getFtpUser(),
                    province.getFtpPassword(), Integer.valueOf(province.getFtpPort()), multipartFile, path);
            String flag = map.get("flag");
            if (UPLOAD_SUCCESS.equals(flag)) {
                String command1 = "rm -rf " + video.getUrl();
                Runtime.getRuntime().exec(command1).waitFor();
            }
        } catch (Exception e) {
            throw new RuntimeException("上传资源至ftp服务器异常：" + e.getMessage(), e);
        }
    }

    /**
     * 媒资发布
     * @param contentId
     */
    @Override
    public void publish(String contentId) {
        List<Video> video = videoService.findByContentId(contentId);
        Content content = contentService.findById(contentId);
        String sequence = NxUtils.createSixBitRandomNum();
        String columnId = ConstantFactory.map.get(NxConstantEnum.columnId.getKey());
        String PPVId = ConstantFactory.map.get(NxConstantEnum.PPVId.getKey());
        PublishResponse response = NxWebserviceSDK.publish(sequence, video.get(0).getId(), PPVId, columnId);
        if(!new Integer(0).equals(response.getResultCode())){
            logger.error("宁夏媒资"+ content.getName() +"发布失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            content.setTitleSpellLong("宁夏媒资"+ content.getName() +"发布失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            contentService.update(content);
        } else {
            //维护注入记录。
            List<ContentOperation> operations = contentOperationService.findByContentIdOrderByCreateDateDesc(contentId);
            if(operations.size() <= 0){
                // 保存内容采集记录表
                ContentOperation operation = new ContentOperation();
                operation.setContent(content);
                operation.setCorrelateId(contentId);
                operation.setInfo("在发布中新增子集发布记录");
                operation.setStatus(4);
                contentOperationService.save(operation);
            } else {
                ContentOperation contentOperation = operations.get(0);
                contentOperation.setStatus(4);
                contentOperationService.update(contentOperation);
            }
            content.setSynType(4);
            content.setTitleSpellLong("seccess");
            contentService.update(content);
            //判断子集状态全部为绑定产品的话。就修改剧头状态
            List<Content> ckContents = contentService.findByPIdAndSynType(content.getpId(), 4);
            Content pContent = contentService.findById(content.getpId());
            if(ckContents.size() == pContent.getSumNum()){
                pContent.setSynType(4);
                contentService.update(pContent);
            }
        }

    }

    /**
     * 取消发布
     * @param contentId
     */
    @Override
    public void unPublish(String contentId) {
        List<Video> video = videoService.findByContentId(contentId);
        List<ContentOperation> operations = contentOperationService.findByContentIdOrderByCreateDateDesc(contentId);
        if(operations.size() <= 0){
            throw new RuntimeException("发布记录为空。不能取消发布");
        }
        ContentOperation operation = operations.get(0);
        Content content = operation.getContent();
        UnpublishResponse response = NxWebserviceSDK.unPublish(operation.getCorrelateId(), video.get(0).getId());
        if(!new Integer(0).equals(response.getResultCode())){
            logger.error("宁夏媒资"+ content.getName() +"取消发布失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            content.setTitleSpellLong("宁夏媒资"+ content.getName() +"取消发布失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            contentService.update(content);
        } else {
            //维护注入记录。
            content.setSynType(2);
            contentService.update(content);
            operation.setStatus(2);
            contentOperationService.update(operation);
            //判断子集状态全部为绑定产品的话。就修改剧头状态
            List<Content> ckContents = contentService.findByPIdAndSynType(content.getpId(), 2);
            Content pContent = contentService.findById(content.getpId());
            if(ckContents.size() == pContent.getSumNum()){
                pContent.setSynType(2);
                contentService.update(pContent);
            }
        }
    }

    /**
     * 产品绑定
     * @param contentId
     */
    @Override
    public void bindProduct(String contentId, Integer type) {
        List<Video> video = videoService.findByContentId(contentId);
        Content content = contentService.findById(contentId);
        List<ContentOperation> operations = contentOperationService.findByContentIdOrderByCreateDateDesc(contentId);
        if(operations.size() <= 0){
            throw new RuntimeException("发布记录为空。不能绑定");
        }
        ContentOperation operation = operations.get(0);
        String sequence = operation.getCorrelateId();
        String PPVId = "";
        if(type == 3){
            PPVId = ConstantFactory.map.get(NxConstantEnum.PPVId.getKey());
        }else if(type == 5){
            PPVId = ConstantFactory.map.get(NxConstantEnum.FREE_PPVId.getKey());
        }
        // auto 逻辑未完成
        String operate = "Add";
//        String operate = "Update";
        BindProductResponse response = NxWebserviceSDK.bingProduct(sequence, operate, video.get(0).getUrl(), PPVId);
        //关于产品包的绑定信息放在那里。待定
        video.get(0).setCdn1Url(PPVId);
        if(!new Integer(0).equals(response.getResultCode())){
            logger.error("宁夏媒资"+ content.getName() +"绑定产品"+ PPVId +"失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            content.setTitleSpellLong("宁夏媒资"+ content.getName() +"绑定产品"+ PPVId +"失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            contentService.update(content);
        } else {
            content.setSynType(5);
            contentService.update(content);
            List<ContentOperation> contentOperations = contentOperationService.findByContentIdOrderByCreateDateDesc(contentId);
            contentOperations.get(0).setBindStatus(1);
            contentOperationService.update(contentOperations.get(0));
            //判断子集状态全部为绑定产品的话。就修改剧头状态
            List<Content> ckContents = contentService.findByPIdAndSynType(content.getpId(), 5);
            Content pContent = contentService.findById(content.getpId());
            if(ckContents.size() == pContent.getSumNum()){
                pContent.setSynType(5);
                contentService.update(pContent);
            }
        }
    }

    /**
     * 删除媒资
     * @param contentId
     */
    @Override
    public void deleteAsset(String contentId) {
        List<Video> video = videoService.findByContentId(contentId);
        Content content = contentService.findById(contentId);
//        List<ContentOperation> operations = contentOperationService.findByContentIdOrderByCreateDateDesc(contentId);
//        if(operations.size() <= 0){
//            throw new RuntimeException("内容注入发布记录为空。不能删除");
//        }
//        ContentOperation operation = operations.get(0);
//        String sequence = operation.getCorrelateId();
        String sequence = NxUtils.createSixBitRandomNum();
        DeleteAssetResponse response = NxWebserviceSDK.deleteAsset(sequence, video.get(0).getId());
        if(!new Integer(0).equals(response.getResultCode())){
            logger.error("宁夏媒资"+ content.getName() +"删除失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            content.setTitleSpellLong("宁夏媒资"+ content.getName() +"删除失败，code:" + response.getResultCode() + ",msg:" + response.getResultMsg());
            contentService.update(content);
        } else {
            content.setSynType(0);
            contentService.update(content);
            //判断子集状态全部为未注入的话。就修改剧头状态
            List<Content> ckContents = contentService.findByPIdAndSynType(content.getpId(), 0);
            Content pContent = contentService.findById(content.getpId());
            if(ckContents.size() == pContent.getSumNum()){
                pContent.setSynType(0);
                contentService.update(pContent);
            }
        }
    }

    /**
     * 获取播放地址
     * @param accesstoken
     * @param temps
     * @param appId
     * @return
     */
    @Override
    public Video getPlayURL(String accesstoken, List<Video> temps, String appId) {
        Video video = new Video();
        try {
            if (temps.size() > 0) {
                System.out.println("进入省网鉴权逻辑===========");
                BeanUtils.copyProperties(temps.get(0), video);
                System.out.println(video.toString());
                String url = nxAuthService.getPlayUrl(accesstoken, video.getUrl());
                if (StringUtils.isBlank(url)) {
                    throw new RuntimeException("通过局方接口获取播放地址异常或错误!!!可能的原因有三方id错误或内容未发布!!!");
                } else {
                    video.setUrl(url);
                }
                return video;
            } else {
                throw new RuntimeException("宁夏鉴权逻辑：====该内容未配置相关视频资源");
            }
        } catch (Exception e) {
            throw new RuntimeException("调用宁夏广电获取播放地址异常!!!" + e.getMessage(), e);
        }
    }
}
