package cn.com.evo.admin.manage.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sys_role_permission database table.
 * 
 */
@Entity
@Table(name = "sys_role_permission")
@NamedQuery(name = "RolePermission.findAll", query = "SELECT r FROM RolePermission r")
public class RolePermission extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public RolePermission() {
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}