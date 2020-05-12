package cn.com.evo.provincial.service;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Video;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rf
 * @date 2019/7/15
 */
@Component
public interface ProvincialService {

    /**
     * 新增电影注入
     * @param contentId
     * @param province
     */
    void registMovie(String contentId, Province province);

    /**
     * 更新电影注入
     * @param contentId
     * @param province
     */
    void updateMovie(String contentId, Province province);

    /**
     * 删除电影注入
     * @param contentId
     * @param province
     */
    void deleteMovie(String contentId, Province province);

    /**
     * 新增剧集注入
     * @param contentId
     * @param province
     */
    void registSeries(String contentId, Province province);

    /**
     * 更新剧集注入
     * @param contentId
     * @param province
     */
    void updateSeries(String contentId, Province province);

    /**
     * 删除剧集注入
     * @param contentId
     * @param province
     */
    void deleteSeries(String contentId, Province province);

    /**
     * 新增子集注入
     * @param contentId
     * @param province
     */
    void registSeriesChild(String contentId, Province province);

    /**
     * 更新子集注入
     * @param contentId
     * @param province
     */
    void updateSeriesChild(String contentId, Province province);

    /**
     * 删除子集注入
     * @param contentId
     * @param province
     */
    void deleteSeriesChild(String contentId, Province province);

    /**
     * 数据拉取
     */
    void pull();

    /**
     * 数据同步
     * @param catalogueRelationId
     */
    void dataSyn(String catalogueRelationId);

    /**
     * 获取播放
     * @param accesstoken
     * @param temps
     * @param appId
     * @return
     */
    Video getPlayURL(String accesstoken, List<Video> temps, String appId);

    /**
     * 上传海报至局方存储(ftp或者cdn之类)
     * @param picture
     * @param province
     */
    void registImage(Picture picture, Province province, boolean deleteFlag);

    /**
     * 上传视频至局方存储(ftp或者cdn之类)
     * @param video
     * @param province
     */
    void registVideo(Video video, Province province);

    /**
     * 媒资发布
     * @param contentId
     */
    void publish(String contentId);

    /**
     * 取消媒资发布
     * @param contentId
     */
    void unPublish(String contentId);

    /**
     * 绑定产品包
     * @param contentId
     */
    void bindProduct(String contentId, Integer type);

    void deleteAsset(String contentId);
}
