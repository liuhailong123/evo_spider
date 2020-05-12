package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class RoleVo extends BaseVo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String description;

    private OrganizationVo organization;

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

    public OrganizationVo getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationVo organization) {
        this.organization = organization;
    }

}
