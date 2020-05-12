package cn.com.evo.cms.domain.vo.pay;

import cn.com.evo.cms.domain.vo.cms.ColumnVo;
import cn.com.evo.cms.domain.vo.vip.UserAccountVo;
import cn.com.evo.cms.domain.vo.vip.UserVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 订单信息表
 * The persistent class for the pay_order_info database table.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String thirdPartyOrderNo;

    private String orderNo;

    private PayConfigVo payConfig;

    private UserVo user;

    private String contentId;

    private UserAccountVo userAccount;

    private ProductVo product;

    private String payAbleMoney;

    private String payAmountMoney;

    private Integer orderType;

    private Integer isManualHandling;

    private Integer manualProcessType;

    private String manualHandlingInfo;

    private ColumnVo app;

    private Date overDate;
}