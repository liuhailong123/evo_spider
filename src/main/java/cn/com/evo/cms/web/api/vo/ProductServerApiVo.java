package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.pay.Rule;
import cn.com.evo.cms.domain.entity.pay.Server;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductServerApiVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String serverName;
    private String serverCode;
    private String serverInfo = "";
    private String ruleName;
    private String ruleInfo = "";
    private String ruleUnit;
    private Integer ruleCount;

    public ProductServerApiVo(Server server, Rule rule) {
        this.serverName = server.getName();
        this.serverCode = server.getCode();
        this.ruleName = rule.getName();
        this.ruleUnit = rule.getUnit();
        this.ruleCount = rule.getCount();
    }
}
