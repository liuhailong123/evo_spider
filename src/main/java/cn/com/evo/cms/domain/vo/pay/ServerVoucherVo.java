package cn.com.evo.cms.domain.vo.pay;

import com.frameworks.core.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务凭证表VO
 * 针对图书会员通过二维码开通小程序会员的功能
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServerVoucherVo extends BaseVo {
    private String code;
    private String userId;
    private String cardNo;
    private String orderNo;
    private String productCode;
    private String payDate;
    private String endDate;
    private Integer status;
}

