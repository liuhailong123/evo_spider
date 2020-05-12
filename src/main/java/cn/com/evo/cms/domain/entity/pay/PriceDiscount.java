package cn.com.evo.cms.domain.entity.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 价格优惠表
 * The persistent class for the cms_content_source database table.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pay_price_discount")
@NamedQuery(name = "PriceDiscount.findAll", query = "SELECT c FROM PriceDiscount c")
public class PriceDiscount extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(254) comment '优惠名称。'")
    private String name;

    @Column(columnDefinition = "varchar(254 ) comment '优惠编码。'")
    private String code;

    @Column(columnDefinition = "int(11) comment '类型。1-满减类；2-折扣类；3-储值类'")
    private Integer type;

    @Column(columnDefinition = "int(11) comment '累积触发。1-是；0-否'")
    private Integer isMore;

    @Column(columnDefinition = "int(11) comment '目标值。单位：分'")
    private String targetValue;

    @Column(columnDefinition = "int(11) comment '优惠值。单位：分'")
    private String discountValue;

    @Column(columnDefinition = "int(11) comment '优先级。越大越优先'")
    private Integer priority;


    public PriceDiscount() {
    }

    public PriceDiscount(String name, String code, Integer type, Integer isMore, String targetValue, String discountValue, Integer priority) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.isMore = isMore;
        this.targetValue = targetValue;
        this.discountValue = discountValue;
        this.priority = priority;
    }
}