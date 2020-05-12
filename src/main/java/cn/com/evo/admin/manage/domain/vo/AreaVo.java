package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class AreaVo extends BaseVo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String shortName;
    private String jd;
    private String wd;
    private int level;
    private int priority;
    private AreaVo parent;

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public AreaVo getParent() {
        return parent;
    }

    public void setParent(AreaVo parent) {
        this.parent = parent;
    }

}
