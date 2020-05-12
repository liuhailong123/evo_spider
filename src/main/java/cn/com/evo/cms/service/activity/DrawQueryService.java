package cn.com.evo.cms.service.activity;

import cn.com.evo.cms.domain.entity.activity.Draw;
import cn.com.evo.cms.domain.entity.activity.DrawQuery;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface DrawQueryService extends BaseService<DrawQuery, String> {


    /**
     * 用户剩余抽奖次数
     *
     * @param userId 用户id
     * @param draw   抽奖池对象
     * @return false-未超过限制数量；true-超过限制数量
     */
    Integer residueCount(String userId, Draw draw);

    /**
     * 开始抽奖
     *
     * @param userId
     * @param cardNo
     * @param appId
     * @param draw
     */
    DrawQuery draw(String userId, String cardNo, String appId, Draw draw);

    /**
     * 获取抽奖记录
     *
     * @param userId
     * @param appId
     * @return
     */
    List<DrawQuery> findByUserIdAndAppId(String userId, String appId);

    /**
     * 获取抽奖记录
     *
     * @param isOwn 是否中奖
     * @param appId 应用id
     * @return
     */
    List<DrawQuery> findByIsOwnAndAppId(Integer isOwn, String appId);
}
