package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.Video;
import com.frameworks.core.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService extends BaseService<Video, String> {

    /**
     * 根据资源id 获取资源关联的 视频对象 List
     *
     * @param sourceId
     * @return
     */
    List<Video> findBySourceId(String sourceId);

    /**
     * 根据资源id 删除资源关联的 视频对象 List
     *
     * @param sourceId
     */
    void deleteBySourceId(String sourceId);

    Long queryVideoCount();


    /**
     * 获取 时长 大小 为空的 视频对象 List
     *
     * @param count 需要获取的 条数
     * @return
     */
    List<Video> getListWhereSizeAndTimeIsnull(int count);

    /**
     * 根据内容Id获取内容下关联的视频List
     *
     * @param contentId
     * @return
     */
    List<Video> findByContentId(String contentId);

    /**
     * 视频资源excel导入
     *
     * @param files
     */
    void importFile(MultipartFile[] files,int isOverwrite);

    /**
     * 视频上传
     *
     * @param files 视频文件
     * @param video 视频对象
     */
    void videoUpload(MultipartFile[] files, Video video);

    /**
     * 视频上传
     *
     * @param file       视频文件
     * @param definition 清晰度
     * @param platform   平台来源
     */
    void videoUpload(MultipartFile file, Integer definition, String platform);

    /**
     * 视频上传
     *
     * @param files   视频文件
     * @param video   视频source对象
     * @param dirName 文件夹名称，例如：HD1080_7 HD720_2 等
     */
    void videoHlsUpload(MultipartFile[] files, Video video, String dirName);

    /**
     * 视频上传
     *
     * @param file       视频文件
     * @param definition 清晰度
     * @param dirName    文件夹名称，例如：HD1080_7 HD720_2 等
     * @param platform   平台来源
     */
    void videoHlsUpload(MultipartFile file, Integer definition, String dirName, String platform);

    /**
     * 获取视频对象
     *
     * @param url 视频地址
     * @return
     */
    Video getByUrl(String url);

    /**
     * 获取视频对象
     *
     * @param url
     * @param sourceId
     * @return
     */
    Video getByUrlAndSourceId(String url, String sourceId);

    /**
     * 根据名称模糊查询视频资源
     *
     * @param name
     * @return
     */
    List<Video> findLikeName(String name);

    /**
     * 根据名称查询视频资源
     *
     * @param name
     * @return
     */
    List<Video> findByName(String name);

    /**
     * 根据sourceId查询视频
     * @param sourceId
     * @return
     */
    Video getBySourceId(String sourceId);
}
