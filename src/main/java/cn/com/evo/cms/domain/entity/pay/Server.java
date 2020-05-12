package cn.com.evo.cms.domain.entity.pay;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pay_server")
@NamedQuery(name = "Server.findAll", query = "SELECT q FROM Server q")
public class Server extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(64) COMMENT '服务名称'")
    private String name;

    @Column(columnDefinition = "varchar(10) COMMENT '服务编码'")
    private String code;

    @OneToMany(mappedBy = "server", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    private List<ServerRuleRel> serverRuleRels = new ArrayList<ServerRuleRel>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ServerRuleRel> getServerRuleRels() {
        return serverRuleRels;
    }

    public void setServerRuleRels(List<ServerRuleRel> serverRuleRels) {
        this.serverRuleRels = serverRuleRels;
    }
}
