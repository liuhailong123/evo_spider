package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.ServerVoucher;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

/**
 * 服务凭证仓储层
 * @author luxin
 */
public interface ServerVoucherRepository extends BaseRepository<ServerVoucher, String> {
    /**
     * 根据订单号获取服务凭证，一个订单号只允许创建一个服务凭证
     *
     * @param orderNo sp线上支付订单号
     * @return 服务凭证对象
     */
    ServerVoucher getByOrderNo(String orderNo);

    /**
     * 根据用户id、智能卡号、产品编码、支付时间 获取服务凭证
     * 以上四个参数，可唯一创建一个服务凭证
     *
     * @param userId      用户id
     * @param cardNo      智能卡号
     * @param productCode sp产品编码
     * @param payDate     支付时间
     * @return 服务凭证对象
     */
    ServerVoucher getByUserIdAndCardNoAndProductCodeAndPayDate(String userId, String cardNo, String productCode, String payDate);
}

