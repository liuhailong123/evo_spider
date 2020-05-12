package cn.com.evo.integration.chongqing;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.domain.entity.total.ContentOperation;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.VideoService;
import cn.com.evo.cms.service.total.ContentOperationService;
import cn.com.evo.integration.chongqing.common.CQConstantEnum;
import cn.com.evo.integration.chongqing.common.CqUtils;
import cn.com.evo.integration.chongqing.content.CQWebServiceSDK;
import cn.com.evo.integration.chongqing.content.CreateXML;
import cn.com.evo.integration.chongqing.content.model.ContentDto;
import cn.com.evo.integration.chongqing.exception.RegestException;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.common.enums.ActionEnum;
import cn.com.evo.integration.common.utils.FileToMulFile;
import cn.com.evo.provincial.service.AbstractProvincialServiceImpl;
import com.frameworks.utils.FtpUtils;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author rf
 * @date 2019/6/5
 */
@Service
@Transactional
public class CqgdContentServiceImpl extends AbstractProvincialServiceImpl {
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

    /**
     * 电影首次注入
     *
     * @param contentId
     * @param province
     */
    @Override
    public void registMovie(String contentId, Province province) {
        // 上传图片和视频文件至ftp服务器
//        moveMoviePictureAndVideoFile(contentId);
        // 电影注入流程
        movieProcess(contentId, province, ActionEnum.REGIST.getValue());
    }

    /**
     * 电影更新注入
     * @param contentId
     * @param province
     */
    @Override
    public void updateMovie(String contentId, Province province) {
        movieProcess(contentId, province, ActionEnum.UPDATE.getValue());
    }

    /**
     * 电影删除注入
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
        List<Content> childs = contentService.findByPIdOrderBySortAsc(episode.getId());
        episodeProcess(episode, childs, province, ActionEnum.REGIST.getValue());
    }

    /**
     * 更新剧集注入
     * @param contentId
     * @param province
     */
    @Override
    public void updateSeries(String contentId, Province province) {
        Content episode = contentService.findById(contentId);
        List<Content> childs = contentService.findByPIdOrderBySortAsc(episode.getId());
        episodeProcess(episode, childs, province, ActionEnum.UPDATE.getValue());
    }

    /**
     * 删除剧集注入
     * @param contentId
     * @param province
     */
    @Override
    public void deleteSeries(String contentId, Province province) {
        Content episode = contentService.findById(contentId);
        List<Content> childs = contentService.findByPIdOrderBySortAsc(episode.getId());
        episodeProcess(episode, childs, province, ActionEnum.DELETE.getValue());
    }

    /**
     * 剧集子集首次注入
     *
     * @param contentId
     * @param province
     */
    @Override
    public void registSeriesChild(String contentId, Province province) {
        List<ContentOperation> contentOperation = contentOperationService.findByContentIdOrderByCreateDateDesc(contentId);
        if(contentOperation.size() > 0 && contentOperation.get(0).getStatus() == 2){
            throw new RegestException("该剧集已经注入成功。请勿重复注入");
        } else {
            Content child = contentService.findById(contentId);
            Content content = contentService.findById(child.getpId());
            // 剧集子集注入逻辑
            childProcess(content, child, province, ActionEnum.REGIST.getValue());
        }
    }

    /**
     * 剧集子集更新操作
     * @param contentId
     * @param province
     */
    @Override
    public void updateSeriesChild(String contentId, Province province) {
        List<ContentOperation> contentOperation = contentOperationService.findByContentIdOrderByCreateDateDesc(contentId);
        if(contentOperation.size() > 0 && contentOperation.get(0).getStatus() != 2){
            throw new RegestException("未注入媒资不允许做更改操作");
        } else {
            Content child = contentService.findById(contentId);
            Content content = contentService.findById(child.getpId());
            // 剧集子集注入逻辑
            childProcess(content, child, province, ActionEnum.UPDATE.getValue());
        }
    }

    /**
     * 剧集子集删除操作
     * @param contentId
     * @param province
     */
    @Override
    public void deleteSeriesChild(String contentId, Province province) {
        List<ContentOperation> contentOperation = contentOperationService.findByContentIdOrderByCreateDateDesc(contentId);
        if(contentOperation.size() > 0 && contentOperation.get(0).getStatus() != 2){
            throw new RegestException("未注入媒资不允许做删除操作");
        } else {
            Content child = contentService.findById(contentId);
            Content content = contentService.findById(child.getpId());
            // 剧集子集注入逻辑
            childProcess(content, child, province, ActionEnum.DELETE.getValue());
        }
    }

    /**
     * 通过content获取图片信息
     * @param content
     * @param child
     * @param province
     * @param action
     */
    private void childProcess(Content content, Content child, Province province, String action) {
        try {
            if(content == null && child == null){
                throw new RuntimeException("内容不存在!!!无法进行注入!!!");
            }

            // 获取内容对应图片对象list
            List<Picture> pictures = pictureService.findByContentId(content.getId());
            //生成子集信息xml
            createSeriesChildXml(content, child, pictures, province, action);

        }catch (Exception e){
            throw new RuntimeException("根据剧集子集内容创建xml异常！！！" + e.getMessage(), e);
        }
    }

    @Override
    public Video getPlayURL(String accesstoken, List<Video> temps, String appId) {
        Video video = new Video();
        BeanUtils.copyProperties(temps.get(0), video);
        return video;
    }

    /**
     * 生成子集信息xml并调用局方接口
     * @param content
     * @param child
     * @param pictures
     * @param province
     * @param action
     */
    private void createSeriesChildXml(Content content, Content child, List<Picture> pictures,
                                      Province province, String action) {
        try {
            child = setChild(child, content);
            ContentDto dto = new ContentDto();
            List<Video> videos = videoService.findByContentId(child.getId());
            dto.setContent(child);
            dto.setVideo(videos.get(0));
            // 获取内容对应海报
            Picture hPicture = null;
            Picture sPicture = null;
            for (Picture picture : pictures) {
                if (picture.getType() == 1) {
                    hPicture = picture;
                }
                if (picture.getType() == 2) {
                    sPicture = picture;
                }
            }
            String str = CreateXML.createSeriesChild(content, dto, hPicture, sPicture, province, action);

            String localPath = CqUtils.createLoaclXML(child.getId(), str, province);
            //上传xml文件到ftp服务器并返回ftp路径
            String xmlFtpuRL = uploadXml(localPath, province);

            // 调用局方接口：通知局方进行内容采集
            logger.error("开始注入:" + child.getName());
            String correlateId = ConstantFactory.map.get(CQConstantEnum.csp_id.getKey()) + CqUtils.createSixBitRandomNum();
            CQWebServiceSDK.callCmd(correlateId, xmlFtpuRL);
            // 更新内容注入状态
            Content temp = contentService.findById(child.getId());
            temp.setSynType(1);
            contentService.update(temp);

            // 保存内容采集记录表
            ContentOperation contentOperation = new ContentOperation();
            contentOperation.setContent(temp);
            contentOperation.setCorrelateId(correlateId);
            contentOperation.setInfo(xmlFtpuRL);
            contentOperation.setStatus(1);
            contentOperationService.save(contentOperation);
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口：通知局方进行内容采集异常" + e.getMessage(), e);
        }
    }

    /**
     * 复制主剧集详细属性
     * @param child
     * @param content
     * @return
     */
    private Content setChild(Content child, Content content) {
        child.setInfo(content.getInfo());
        child.setYear(content.getYear());
        child.setActorTags(content.getActorTags());
        child.setDirectorTags(content.getDirectorTags());
        child.setYearTags(content.getYearTags());
        child.setAreaTags(content.getAreaTags());
        child.setLanguage(content.getLanguage());
        child.setGrade(content.getGrade());
        return child;
    }

    private void episodeProcess(Content episode, List<Content> childs, Province province, String action) {
        String xmlStr = "";
        try {
            if(episode == null){
                throw new RuntimeException("内容不存在!!!无法进行注入!!!");
            }
            if(childs.size() < 1){
                throw new RuntimeException("内容不存在!!!无法进行注入!!!");
            }

            // 获取内容对应图片对象list
            List<Picture> pictures = pictureService.findByContentId(episode.getId());

            createSeriesXml(episode, childs, pictures, province, action);

        }catch (Exception e){
            throw new RuntimeException("根据剧集子集内容创建xml异常！！！" + e.getMessage(), e);
        }
    }

    /**
     * 剧集注入流程
     * @param episode
     * @param childs
     * @param pictures
     * @param province
     */
    private void createSeriesXml(Content episode, List<Content> childs, List<Picture> pictures,
                                 Province province, String action) {
        try {
            List<ContentDto> dtos = new ArrayList<>();
            for (Content child : childs) {
                ContentDto dto = new ContentDto();
                List<Video> videos = videoService.findByContentId(child.getId());
                dto.setContent(child);
                dto.setVideo(videos.get(0));
                dtos.add(dto);
            }
            // 获取内容对应海报
            Picture hPicture = null;
            Picture sPicture = null;
            for (Picture picture : pictures) {
                if (picture.getType() == 1) {
                    hPicture = picture;
                }
                if (picture.getType() == 2) {
                    sPicture = picture;
                }
            }
            String str = CreateXML.createSeriesInfo(episode, hPicture, sPicture, province, action);

            String localPath = CqUtils.createLoaclXML(episode.getId(), str, province);
            //上传xml文件到ftp服务器并返回ftp路径
            String xmlFtpuRL = uploadXml(localPath, province);

            // 调用局方接口：通知局方进行内容采集
            logger.error("开始注入剧集头信息:" + episode.getName());
            String correlateId = ConstantFactory.map.get(CQConstantEnum.csp_id.getKey()) + CqUtils.createSixBitRandomNum();
            CQWebServiceSDK.callCmd(correlateId, xmlFtpuRL);

            // 更新内容注入状态
            Content temp = contentService.findById(episode.getId());
            temp.setSynType(1);
            contentService.update(temp);

            // 保存内容采集记录表
            ContentOperation contentOperation = new ContentOperation();
            contentOperation.setContent(temp);
            contentOperation.setCorrelateId(correlateId);
            contentOperation.setInfo(xmlFtpuRL);
            contentOperation.setStatus(1);
            contentOperationService.save(contentOperation);
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口：通知局方进行内容采集异常" + e.getMessage(), e);
        }
    }

    /**
     * 电影注入
     * @param contentId
     * @param province
     */
    private void movieProcess(String contentId, Province province, String action) {
        String xmlStr = "";
        String localPath = "";
        try {
            // 获取内容对象
            Content content = contentService.findById(contentId);

            // 创建子内容对象
            Content child = new Content();
            BeanUtils.copyProperties(content, child);
            child.setId(child.getId() + "_1");
            child.setSort(1);

            // 获取内容相关视频对象list
            Video video = new Video();
            List<Video> videos = videoService.findByContentId(contentId);
            if (videos.size() > 0) {
                // 设置视频对象
                video = videos.get(0);
            }

            // 获取内容对应海报
            Picture hPicture = null;
            Picture sPicture = null;
            List<Picture> pictures = pictureService.findByContentId(contentId);
            for (Picture picture : pictures) {
                if (picture.getType() == 1) {
                    hPicture = picture;
                }
                if (picture.getType() == 2) {
                    sPicture = picture;
                }
            }
            // 生成xml字符串
            xmlStr = CreateXML.createMovie(content, video, hPicture, sPicture, province, action);
        } catch (Exception e) {
            throw new RuntimeException("根据电影内容生成xml字符串异常！！！" + e.getMessage(), e);
        }
        try {
            localPath = CqUtils.createLoaclXML(contentId, xmlStr, province);
        } catch (Exception e) {
            throw new RuntimeException("创建xml文件异常" + e.getMessage(), e);
        }
        try {
            //上传xml文件到fto服务器并返回ftp路劲
            String xmlFtpuRL = registFileToFtp(localPath, province);
            // 调用局方接口：通知局方进行内容采集
            String correlateId = ConstantFactory.map.get(CQConstantEnum.csp_id.getKey()) + CqUtils.createSixBitRandomNum();
            CQWebServiceSDK.callCmd(correlateId, xmlFtpuRL);

            // 更新内容注入状态
            Content content = contentService.findById(contentId);
            content.setSynType(1);
            contentService.update(content);

            // 保存内容采集记录表
            ContentOperation contentOperation = new ContentOperation();
            contentOperation.setContent(content);
            contentOperation.setCorrelateId(correlateId);
            contentOperation.setInfo(xmlFtpuRL);
            contentOperation.setStatus(1);
            contentOperationService.save(contentOperation);
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口：通知局方进行内容采集异常" + e.getMessage(), e);
        }
    }

    /**
     * 上传xml-ftp
     * @param fileLocalPath
     * @param province
     * @return
     */
    public String uploadXml(String fileLocalPath, Province province){
        FtpClient ftpClient = FtpClient.create();
        SocketAddress addr = new InetSocketAddress(province.getFtpHost(), Integer.parseInt(province.getFtpPort()));
        try {
            ftpClient.connect(addr);
            ftpClient.login(province.getFtpUser(), province.getFtpPassword().toCharArray());
            String path = ConstantFactory.map.get(CQConstantEnum.ftp_xml_path.getKey());
            String[] split = fileLocalPath.split("/");
            ftpClient.changeDirectory(path);
            String command = "chmod 777 " + fileLocalPath;
            Runtime.getRuntime().exec(command).waitFor();
            File file_in = new File(fileLocalPath);

            try(OutputStream os = ftpClient.putFileStream(split[split.length-1]);
                FileInputStream is = new FileInputStream(file_in)) {
                byte[] bytes = new byte[1024];
                int c;
                while ((c = is.read(bytes)) != -1) {
                    os.write(bytes, 0, c);
                }
                System.out.println("upload success");


            } catch (IOException ex) {
                System.out.println("not upload");
                ex.printStackTrace();
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }
            String ftpPath = "ftp://" + province.getFtpUser() +
                    ":" + province.getFtpPassword() +
                    "@" + province.getFtpUrl() + ":" + province.getFtpPort();
            System.out.println(ftpPath + "/" + path + split[split.length-1]);
            return ftpPath + "/" + path + split[split.length-1];
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(ftpClient != null){
                try {
                    ftpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("--------------------失败");
        return "";
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
//            String path = ConstantFactory.map.get(CQConstantEnum.ftp_xml_path.getKey()) + "/" + name;
            String path = ConstantFactory.map.get(CQConstantEnum.ftp_xml_path.getKey()) + "/" + name;
            //上传
            Map<String, String> map = FtpUtils.upload(province.getFtpUrl(), province.getFtpUser(),
                    province.getFtpPassword(), Integer.valueOf(province.getFtpPort()), multipartFile, path);

            String flag = map.get("flag");
            System.out.println(flag);
            if (UPLOAD_SUCCESS.equals(flag)) {
                String command1 = "rm -rf " + fileLocalPath;
                Runtime.getRuntime().exec(command1).waitFor();
                // xml的ftp访问路径
                String ftpPath = "ftp://" + province.getFtpUser() +
                        ":" + province.getFtpPassword() +
                        "@" + province.getFtpUrl() + ":" + province.getFtpPort();
                return ftpPath + "/" + path;
            } else {
                throw new RuntimeException("上传资源至ftp服务器异常");
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
     * 上传图片到ftp
     * @param picture
     * @param province
     */
    @Override
    public void registImage(Picture picture, Province province, boolean deleteFlag) {
        try {
            String command = "chmod 777 " + picture.getCloudPath();
            Runtime.getRuntime().exec(command).waitFor();

            FtpClient ftpClient = cn.com.evo.integration.chongqing.common.FtpUtils.connectFTP(province.getFtpHost(), Integer.parseInt(province.getFtpPort()), province.getFtpUser(), province.getFtpPassword());
            String ftpPath = ConstantFactory.map.get(CQConstantEnum.ftp_image_path.getKey());
            cn.com.evo.integration.chongqing.common.FtpUtils.upload(picture.getCloudPath(), ftpPath, picture.getId() + "." + picture.getExt(), ftpClient);
//
//
//
//            MultipartFile multipartFile = FileToMulFile.getMulFileByPath(picture.getCloudPath(), picture.getFileName());
//
//            // 匹配省网逻辑
//            String name = picture.getFileName();
//            String path = "pub/image/";
//            Map<String, String> map = FtpUtils.upload(province.getFtpUrl(), province.getFtpUser(),
//                    province.getFtpPassword(), Integer.valueOf(province.getFtpPort()), multipartFile, path);
//            String flag = map.get("flag");
//            if (UPLOAD_SUCCESS.equals(flag)) {
//                String command1 = "rm -rf " + picture.getCloudPath();
//                Runtime.getRuntime().exec(command1).waitFor();
//            }else {
//                throw new RuntimeException("上传资源至ftp服务器异常");
//            }
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

            FtpClient ftpClient = cn.com.evo.integration.chongqing.common.FtpUtils.connectFTP(province.getFtpHost(), Integer.parseInt(province.getFtpPort()), province.getFtpUser(), province.getFtpPassword());
            String ftpPath = ConstantFactory.map.get(CQConstantEnum.ftp_video_path.getKey());
            cn.com.evo.integration.chongqing.common.FtpUtils.upload(video.getUrl(), ftpPath, video.getId() + "." + video.getExt(), ftpClient);
            //删除本地资源，防止占用导致上传失败
            String command1 = "rm -rf " + video.getUrl();
            Runtime.getRuntime().exec(command1).waitFor();
//
//            MultipartFile multipartFile = FileToMulFile.getMulFileByPath(video.getUrl(), video.getId()+ "." + video.getExt());
//            // 匹配省网逻辑
//            String name = video.getId();
//            String path = "pub/video/" + name+ "." + video.getExt();
//            Map<String, String> map = FtpUtils.upload(province.getFtpUrl(), province.getFtpUser(),
//                    province.getFtpPassword(), Integer.valueOf(province.getFtpPort()), multipartFile, path);
//            String flag = map.get("flag");
//            if (UPLOAD_SUCCESS.equals(flag)) {
//                String command1 = "rm -rf " + video.getUrl();
//                Runtime.getRuntime().exec(command1).waitFor();
//            } else {
//                throw new RuntimeException("上传资源至ftp服务器异常");
//            }
        } catch (Exception e) {
            throw new RuntimeException("上传资源至ftp服务器异常：" + e.getMessage(), e);
        }
    }

}
