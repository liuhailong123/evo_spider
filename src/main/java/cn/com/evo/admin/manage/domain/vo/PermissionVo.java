package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class PermissionVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String description;

    private ModuleVo module;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ModuleVo getModule() {
        return module;
    }

    public void setModule(ModuleVo module) {
        this.module = module;
    }

}
