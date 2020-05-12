package cn.com.evo.cms.domain.vo.pay;

import com.frameworks.core.entity.BaseEntity;
import com.frameworks.core.vo.BaseVo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class RuleVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private String unit;

    private Integer count;

    private String takeEffectTime;

    private String loseEfficacyTime;

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

}
