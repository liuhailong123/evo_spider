package cn.com.evo.cms.domain.entity.pay;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pay_rule")
@NamedQuery(name = "Rule.findAll", query = "SELECT q FROM Rule q")
public class Rule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(64) COMMENT '规则名称'")
    private String name;

    @Column(columnDefinition = "varchar(64) COMMENT '单位'")
    private String unit;

    @Column(columnDefinition = "int(5) COMMENT '数量'")
    private Integer count;

    @Column(columnDefinition = "varchar(20) COMMENT '生效时间'")
    private String takeEffectTime;

    @Column(columnDefinition = "varchar(20) COMMENT '失效时间'")
    private String loseEfficacyTime;

    @OneToMany(mappedBy = "rule", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    private List<ServerRuleRel> serverRuleRels = new ArrayList<ServerRuleRel>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTakeEffectTime() {
        return takeEffectTime;
    }

    public void setTakeEffectTime(String takeEffectTime) {
        this.takeEffectTime = takeEffectTime;
    }

    public String getLoseEfficacyTime() {
        return loseEfficacyTime;
    }

    public void setLoseEfficacyTime(String loseEfficacyTime) {
        this.loseEfficacyTime = loseEfficacyTime;
    }

    public List<ServerRuleRel> getServerRuleRels() {
        return serverRuleRels;
    }

    public void setServerRuleRels(List<ServerRuleRel> serverRuleRels) {
        this.serverRuleRels = serverRuleRels;
    }
}
