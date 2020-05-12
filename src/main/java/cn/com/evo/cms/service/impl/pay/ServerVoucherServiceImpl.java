package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.pay.ServerVoucher;
import cn.com.evo.cms.repository.pay.ServerVoucherRepository;
import cn.com.evo.cms.service.pay.ProductService;
import cn.com.evo.cms.service.pay.ServerVoucherService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.utils.AESUtils;
import com.frameworks.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 服务凭证服务层实现
 *
 * @author luxin
 */
@Service
@Transactional
public class ServerVoucherServiceImpl extends AbstractBaseService<ServerVoucher, String> implements ServerVoucherService {

    @Autowired
    private ServerVoucherRepository serverVoucherRepository;
    @Autowired
    private ProductService productService;


    @Override
    protected BaseRepository<ServerVoucher, String> getBaseRepository() {
        return serverVoucherRepository;
    }

    @Override
    public ServerVoucher createVoucher(Order order) {
        // 是否需要创建凭证
        boolean hasBookServer = productService.hasBookServer(order.getProduct().getCode());
        if (hasBookServer) {
            // 是否已创建过凭证
            ServerVoucher serverVoucher = serverVoucherRepository.getByOrderNo(order.getOrderNo());
            if (serverVoucher == null) {
                // 未创建过凭证则根据订单信息生成对应凭证
                serverVoucher = new ServerVoucher("", order.getUser().getId(), order.getUserAccount().getAccountNo(),
                        order.getOrderNo(), order.getProduct().getCode(), DateUtil.format(order.getOverDate(), DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS),
                        DateUtil.format(productService.getOverDate(order.getOverDate(), order.getProduct().getCode()),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS), 1);

                // 凭证码
                String encrypt = AESUtils.aesEncrypt(serverVoucher.toJsonParamsForAes().toJSONString());
                serverVoucher.setCode(encrypt);
                serverVoucherRepository.save(serverVoucher);

                return serverVoucher;
            } else {
                // 创建过凭证，则直接返回凭证
                if (serverVoucher.getStatus() == 0) {
                    // 该凭证已失效
                    return null;
                } else {
                    if (DateUtil.stringToDate(serverVoucher.getEndDate()).getTime() >= System.currentTimeMillis()) {
                        // 未过期
                        return serverVoucher;
                    } else {
                        // 已过期
                        return null;
                    }
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public ServerVoucher createVoucher(String userId, String cardNo, String productCode, String payDate) {
        // 是否需要创建凭证
        boolean hasBookServer = productService.hasBookServer(productCode);
        if (hasBookServer) {
            // 是否已创建过凭证
            ServerVoucher serverVoucher = serverVoucherRepository.getByUserIdAndCardNoAndProductCodeAndPayDate(userId,
                    cardNo, productCode, payDate);
            if (serverVoucher == null) {
                // 未创建过凭证则生成对应凭证
                Date overDate = productService.getOverDate(DateUtil.stringToDate(payDate), productCode);
                serverVoucher = new ServerVoucher("", userId, cardNo, "", productCode,
                        payDate, DateUtil.format(overDate,DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS), 1);

                // 凭证码
                String encrypt = AESUtils.aesEncrypt(serverVoucher.toJsonParamsForAes().toJSONString());
                serverVoucher.setCode(encrypt);
                serverVoucherRepository.save(serverVoucher);

                return serverVoucher;
            } else {
                // 创建过凭证，则直接返回凭证
                if (serverVoucher.getStatus() == 0) {
                    // 该凭证已失效
                    return null;
                } else {
                    Date overDate = productService.getOverDate(DateUtil.stringToDate(serverVoucher.getPayDate()), productCode);
                    if (overDate.getTime() >= System.currentTimeMillis()) {
                        // 未过期
                        return serverVoucher;
                    } else {
                        // 已过期
                        return null;
                    }
                }
            }
        } else {
            return null;
        }
    }
}

