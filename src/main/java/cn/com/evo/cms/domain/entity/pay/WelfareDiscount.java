package cn.com.evo.cms.domain.entity.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 福利优惠表
 * The persistent class for the cms_content_source database table.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pay_welfare_discount")
@NamedQuery(name = "WelfareDiscount.findAll", query = "SELECT c FROM WelfareDiscount c")
public class WelfareDiscount extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(254) comment '优惠名称。'")
    private String name;

    @Column(columnDefinition = "varchar(11) comment '优惠编码。'")
    private String code;

    @Column(columnDefinition = "int(11) comment '类型。1-虚拟；2-实物；'")
    private int type;

    @Column(columnDefinition = "varchar(32) comment '商品id。'")
    private String goodsId;
}