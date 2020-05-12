package cn.com.evo.cms.domain.entity.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 产品与支付方式关系
 * The persistent class for the cms_content_source database table.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pay_product_paytype_rel")
@NamedQuery(name = "ProductPayTypeRel.findAll", query = "SELECT c FROM ProductPayTypeRel c")
public class ProductPayTypeRel extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) comment '产品id。'")
    private String productId;

    @Column(columnDefinition = "varchar(32) comment '支付方式id。'")
    private String configId;
}