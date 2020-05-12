package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.pay.Product;
import com.frameworks.core.service.BaseService;

import java.util.Date;
import java.util.List;

/**
 * 订单服务service
 *
 * @author shilinxiao
 */
public interface OrderService extends BaseService<Order, String> {

    /**
     * 根据三方订单号获取订单对象
     *
     * @param thirdPartyOrderNo 三方订单号
     * @return
     */
    Order getByThirdPartyOrderNo(String thirdPartyOrderNo);

    /**
     * 根据订单号获取订单对象
     *
     * @param orderNo 订单号
     * @return
     */
    Order getByOrderNo(String orderNo);

    /**
     * 根据用户id和订单状态获取订单列表，并按照创建时间排序倒序
     *
     * @param userId
     * @param orderType
     * @param appId
     * @return
     */
    List<Order> findByUserIdAndOrderTypeAndAppIdOrderByCreateDateDesc(String userId, int orderType, String appId);

    /**
     * 产品订购鉴权
     *
     * @param serverCode 服务编码
     * @param userId     用户id
     * @param appId      应用id
     * @param list       产品列表
     * @return false-未过期；true-已过期
     */
    boolean orderAuth(String serverCode, String userId, String appId, List<Product> list);

    /**
     * 人工处理
     *
     * @param order
     */
    void manualProcess(Order order);

    /**
     * 创建订单
     *
     * @param thirdOrderNo   三方订单号
     * @param payConfigId
     * @param userId
     * @param productId
     * @param payAbleMoney
     * @param payAmountMoney
     * @param payType
     * @param appId
     * @param accountId
     * @return
     */
    Order createOrder(String thirdOrderNo, String payConfigId, String userId,
                      String productId, String payAbleMoney, String payAmountMoney,
                      Integer payType, String appId, String accountId);

    /**
     * 根据用户id、sp产品编码、支付状态、完成时间、应用id获取订单
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
