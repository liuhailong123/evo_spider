package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class OrganizationRoleVo extends BaseVo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private OrganizationVo organization;

    private RoleVo role;

    public OrganizationVo getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationVo organization) {
        this.organization = organization;
    }

    public RoleVo getRole() {
        return role;
    }

    public void setRole(RoleVo role) {
        this.role = role;
    }

}
