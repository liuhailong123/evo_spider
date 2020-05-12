package cn.com.evo.cms.domain.entity.pay;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pay_server_rule_rel")
@NamedQuery(name = "ServerRuleRel.findAll", query = "SELECT q FROM ServerRuleRel q")
public class ServerRuleRel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(64) COMMENT '产品名称'")
    private String name;

    @ManyToOne
    @JoinColumn(name = "server_id",columnDefinition = "varchar(32) COMMENT '服务id'")
    private Server server;

    @ManyToOne
    @JoinColumn(name = "rule_id",columnDefinition = "varchar(32) COMMENT '规则id'")
    private Rule rule;

    @OneToMany(mappedBy = "serverRuleRel", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<ProductServerRel> productServerRels = new ArrayList<ProductServerRel>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public List<ProductServerRel> getProductServerRels() {
        return productServerRels;
    }

    public void setProductServerRels(List<ProductServerRel> productServerRels) {
        this.productServerRels = productServerRels;
    }
}
