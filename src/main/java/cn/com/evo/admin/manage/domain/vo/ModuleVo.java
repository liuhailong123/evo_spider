package cn.com.evo.admin.manage.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.frameworks.core.vo.BaseVo;

public class ModuleVo extends BaseVo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String code;
    private String icon;
    private String url;
    private int priority;
    private int status;
    private String description;
    private int level;
    private ModuleVo parent;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ModuleVo getParent() {
        return parent;
    }

    public void setParent(ModuleVo parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
