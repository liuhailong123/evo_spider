package cn.com.evo.cms.service.activity;

import cn.com.evo.cms.domain.entity.activity.Draw;
import cn.com.evo.cms.domain.entity.activity.DrawQuery;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface DrawService extends BaseService<Draw, String> {

    /**
     * 获取抽奖列表
     *
     * @param enable 是否启用
     * @param appId  应用id
     * @return
     */
    List<Draw> findByEnableAndAppId(Integer enable, String appId);

    /**
     * 获取可用的抽奖活动
     *
     * @param appId
     * @return
     */
    Draw getAvailableDraw(String appId);
}
