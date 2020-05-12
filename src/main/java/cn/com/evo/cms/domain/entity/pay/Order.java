package cn.com.evo.cms.domain.entity.pay;

import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import com.frameworks.core.web.constants.WebConsts;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * 订单信息表
 * The persistent class for the pay_order_info database table.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pay_order_info")
@NamedQuery(name = "Order.findAll", query = "SELECT c FROM Order c")
public class Order extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(254) comment '三方订单号'")
    private String thirdPartyOrderNo;

    @Column(columnDefinition = "varchar(254) comment '订单号'")
    private String orderNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payConfigId", columnDefinition = "varchar(32) comment '支付方式id'")
    private PayConfig payConfig;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", columnDefinition = "varchar(32) comment '用户id'")
    private User user;

    @Column(columnDefinition = "varchar(32) comment '内容id'")
    private String contentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", columnDefinition = "varchar(32) comment '账号id'")
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", columnDefinition = "varchar(32) comment '产品套餐id'")
    private Product product;

    @Column(columnDefinition = "varchar(32) comment '应付金额'")
    private String payAbleMoney;

    @Column(columnDefinition = "varchar(32) comment '实付金额'")
    private String payAmountMoney;

    @Column(columnDefinition = "int(11) comment '订单状态0未支付1已支付2支付失败3已退订'")
    private Integer orderType;

    @Column(columnDefinition = "int(11) comment '是否人工处理'")
    private Integer isManualHandling;

    @Column(columnDefinition = "int(11) comment '人工处理类型'")
    private Integer manualProcessType;

    @Column(columnDefinition = "varchar(254) comment '人工处理意见'")
    private String manualHandlingInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appId", columnDefinition = "varchar(32) comment '应用id'")
    private cn.com.evo.cms.domain.entity.cms.Column app;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = WebConsts.DEFAULT_DATETIME_FORMAT)
    @Column(columnDefinition = "datetime comment '完成时间'")
    private Date overDate;
}