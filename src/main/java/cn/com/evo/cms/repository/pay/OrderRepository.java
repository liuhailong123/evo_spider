package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.Order;
import com.frameworks.core.repository.BaseRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order, String> {
    Order getByThirdPartyOrderNo(String thirdPartyOrderNo);

    Order getByOrderNo(String orderNo);

    List<Order> findByUserIdAndOrderTypeAndAppIdOrderByCreateDateDesc(String userId, int orderType, String appId);

    /**
     * 根据用户id、sp产品编码，支付状态、完成时间、appId获取订单
     *
     * @param userId
     * @param productCode
     * @param orderType
     * @param overDate
     * @param appId
     * @return
     */
    Order getByUserIdAndProductCodeAndOrderTypeAndOverDateAndAppId(String userId, String productCode, int orderType, Date overDate, String appId);

    List<Order> findByUserIdAndContentId(String uId, String sId);
}
