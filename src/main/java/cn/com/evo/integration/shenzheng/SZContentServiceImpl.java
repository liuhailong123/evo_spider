package cn.com.evo.integration.shenzheng;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.provincial.service.AbstractProvincialServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rf
 * @date 2019/6/20
 */
@Service
@Transactional
public class SZContentServiceImpl extends AbstractProvincialServiceImpl {
    /**
     * 剧集注入
     * @param contentId
     * @param province
     */
    @Override
    public void registSeries(String contentId, Province province) {

    }

    /**
     * 电影注入
     * @param contentId
     * @param province
     */
    @Override
    public void registMovie(String contentId, Province province) {

    }

    @Override
    public Video getPlayURL(String accesstoken, List<Video> temps, String appId) {
        return temps.get(0);
    }
}
