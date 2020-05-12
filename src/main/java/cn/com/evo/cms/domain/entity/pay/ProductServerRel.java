package cn.com.evo.cms.domain.entity.pay;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "pay_product_server_rel")
@NamedQuery(name = "ProductServerRel.findAll", query = "SELECT q FROM ProductServerRel q")
public class ProductServerRel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "server_rule_rel_id",columnDefinition = "varchar(32) COMMENT '产品配置id'")
    private ServerRuleRel serverRuleRel;

    @ManyToOne
    @JoinColumn(name = "product_id",columnDefinition = "varchar(32) COMMENT '产品套餐id'")
    private Product product;

    public ServerRuleRel getServerRuleRel() {
        return serverRuleRel;
    }

    public void setServerRuleRel(ServerRuleRel serverRuleRel) {
        this.serverRuleRel = serverRuleRel;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
