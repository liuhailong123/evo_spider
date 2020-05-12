package cn.com.evo.cms.domain.vo.pay;

import com.frameworks.core.entity.BaseEntity;
import com.frameworks.core.vo.BaseVo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ServerRuleRelVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private ServerVo server;

    private RuleVo rule;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServerVo getServer() {
        return server;
    }

    public void setServer(ServerVo server) {
        this.server = server;
    }

    public RuleVo getRule() {
        return rule;
    }

    public void setRule(RuleVo rule) {
        this.rule = rule;
    }

}
