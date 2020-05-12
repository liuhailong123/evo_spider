package cn.com.evo.cms.domain.entity.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 产品优惠关系
 * The persistent class for the cms_content_source database table.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pay_product_discount_rel")
@NamedQuery(name = "ProductDiscountRel.findAll", query = "SELECT c FROM ProductDiscountRel c")
public class ProductDiscountRel extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) comment '产品id。'")
    private String productId;

    @Column(columnDefinition = "varchar(32) comment '优惠id。'")
    private String discountId;

    @Column(columnDefinition = "int(11) comment '类型。1-价格优惠；2-福利优惠；'")
    private int type;

    public ProductDiscountRel(String productId, String discountId, int type) {
        this.productId = productId;
        this.discountId = discountId;
        this.type = type;
    }
}