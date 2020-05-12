package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.pay.ServerVoucher;
import com.frameworks.core.service.BaseService;

import java.util.List;

/**
 * 服务凭证服务层
 * @author luxin
 */
public interface ServerVoucherService extends BaseService<ServerVoucher, String> {

    /**
     * 创建凭证(线上订单)
     *
     * @param order sp线上订单
     * @return
     */
    ServerVoucher createVoucher(Order order);

    /**
     * 创建凭证(线下订单)
     *
     * @param userId      用户id
     * @param cardNo      智能卡号
     * @param productCode sp产品编码
     * @param payDate     支付时间
     * @return
     */
    ServerVoucher createVoucher(String userId, String cardNo,
                                String productCode, String payDate);
}

