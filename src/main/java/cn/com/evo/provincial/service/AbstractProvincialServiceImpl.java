package cn.com.evo.provincial.service;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.provincial.exception.NotRealizeException;
import cn.com.evo.provincial.service.ProvincialService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * 抽象实现
 * 封装一些选择性函数。供子类选择调用
 * ps:需要重写
 * @author rf
 * @date 2019/7/16
 */
@Service
@Component
public abstract class AbstractProvincialServiceImpl implements ProvincialService {
    protected Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public void registMovie(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void updateMovie(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void deleteMovie(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void registSeries(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void updateSeries(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void deleteSeries(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void registSeriesChild(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void updateSeriesChild(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void deleteSeriesChild(String contentId, Province province) {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void pull() {
        throw new NotRealizeException("该省网目前还未实现此函数，请确认省网编码或重写函数生效。");
    }

    @Override
    public void dataSyn(String catalogueRelationId) {
        throw new NotRealizeException("暂不支持该省网进行语音搜索数据注入!!!");
    }

    @Override
    public Video getPlayURL(String accesstoken,List<Video> temps, String appId) {
        throw new NotRealizeException("暂不支持该省网进行播放地址获取!!!");
    }

    @Override
    public void registImage(Picture picture, Province province, boolean deleteFlag) {
        logger.error("当前上传为102本地上传。如果需要做省网上传请重写该方法。");
        try {
            String command = "chmod 777 " + picture.getCloudPath();
            Runtime.getRuntime().exec(command).waitFor();

            // 获取海报，进行上传
            File file = new File(picture.getCloudPath());
            if (file.exists()) {
                String command2 = "mv " + picture.getCloudPath() + " /usr/tomcat/apache-tomcat-8.5.32/webapps/cms_picture/" + picture.getFileName();
                Runtime.getRuntime().exec(command2).waitFor();
            }
        } catch (Exception e) {
            throw new RuntimeException("上传资源至tomcat目录异常：" + e.getMessage(), e);
        }
    }

    @Override
    public void registVideo(Video video, Province province) {
        throw new NotRealizeException("暂不支持该省网进行视频上传!!!");
    }

    @Override
    public void publish(String contentId) {
        throw new NotRealizeException("暂不支持该省网进行发布操作!!!");
    }

    @Override
    public void unPublish(String contentId) {
        throw new NotRealizeException("暂不支持该省网进行取消发布!!!");
    }

    @Override
    public void bindProduct(String contentId, Integer type) {
        throw new NotRealizeException("暂不支持该省网进行产品绑定操作!!!");
    }

    @Override
    public void deleteAsset(String contentId) {
        throw new NotRealizeException("暂不支持该省网进行删除媒资操作!!!");
    }
}
