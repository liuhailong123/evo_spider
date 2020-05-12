package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class OrganizationVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private int code;
    
    private int level;

    private int priority;

    private String description;

    private OrganizationVo parent;

    private AreaVo area;

    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrganizationVo getParent() {
        return parent;
    }

    public void setParent(OrganizationVo parent) {
        this.parent = parent;
    }

    public AreaVo getArea() {
        return area;
    }

    public void setArea(AreaVo area) {
        this.area = area;
    }

}
