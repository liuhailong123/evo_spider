package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.LimitFree;
import com.frameworks.core.service.BaseService;

/**
 * 订单服务service
 *
 * @author luxin
 */
public interface LimitFreeService extends BaseService<LimitFree, String> {

    /**
     * 获取业务名称
     *
     * @param id
     * @return
     */
    String getBizName(String id);

    /**
     * 根据业务id获取限免对象
     *
     * @param bizId
     * @param appId
     * @return
     */
    LimitFree getByBizIdAndAppId(String bizId, String appId);

    /**
     * 是否限时免费
     *
     * @param bizId
     * @param appId
     * @return 未配置或者过期的情况下返回false，已配置并且未过期时，返回true
     */
    boolean isFree(String bizId, String appId);
}
