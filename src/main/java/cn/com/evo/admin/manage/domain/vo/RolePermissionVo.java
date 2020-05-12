package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class RolePermissionVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private RoleVo role;

    private PermissionVo permission;

    public RoleVo getRole() {
        return role;
    }

    public void setRole(RoleVo role) {
        this.role = role;
    }

    public PermissionVo getPermission() {
        return permission;
    }

    public void setPermission(PermissionVo permission) {
        this.permission = permission;
    }

}
