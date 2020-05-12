package cn.com.evo.cms.domain.entity.pay;

import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 服务凭证表
 * 针对图书会员通过二维码开通小程序会员的功能
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pay_server_voucher")
@NamedQuery(name = "ServerVoucher.findAll", query = "SELECT q FROM ServerVoucher q")
public class ServerVoucher extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(2000) COMMENT '凭证码'")
    private String code;

    @Column(columnDefinition = "varchar(32) COMMENT '用户id'")
    private String userId;

    @Column(columnDefinition = "varchar(32) COMMENT '智能卡号'")
    private String cardNo;

    @Column(columnDefinition = "varchar(254) COMMENT 'sp订单号'")
    private String orderNo;

    @Column(columnDefinition = "varchar(10) COMMENT '产品套餐编码'")
    private String productCode;

    @Column(columnDefinition = "varchar(32) COMMENT '支付时间'")
    private String payDate;

    @Column(columnDefinition = "varchar(32) COMMENT '到期时间'")
    private String endDate;

    @Column(columnDefinition = "ini(11) COMMENT '凭证类型;1-有效;0-无效'")
    private Integer status;


    public JSONObject toJsonParamsForAes(){
        JSONObject json = new JSONObject();
        json.put("userId",this.userId);
        json.put("cardNo",this.cardNo);
        json.put("orderNo",this.orderNo);
        json.put("productCode",this.productCode);
        json.put("payDate",this.payDate);
        json.put("endDate",this.endDate);
        return json;
    }
}

