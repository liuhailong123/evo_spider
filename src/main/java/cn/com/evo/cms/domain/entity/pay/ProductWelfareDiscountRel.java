package cn.com.evo.cms.domain.entity.pay;

import com.frameworks.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 产品福利优惠关系表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pay_product_welfare_discount_rel")
@NamedQuery(name = "ProductWelfareDiscountRel.findAll", query = "SELECT c FROM ProductWelfareDiscountRel c")
public class ProductWelfareDiscountRel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String productId;

    private String discountId;

    private Integer type;//1 价格 2 福利

}
