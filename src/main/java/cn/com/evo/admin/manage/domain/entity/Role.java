package cn.com.evo.admin.manage.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the sys_role database table.
 * 
 */
@Entity
@Table(name = "sys_role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, length = 60)
    private String code;

    @Column(length = 225)
    private String description;

    @OneToMany(mappedBy = "role", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<AccountRole> accountRoles = new ArrayList<AccountRole>(0);

    @OneToMany(mappedBy = "role", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<OrganizationRole> organizationRoles = new ArrayList<OrganizationRole>(0);

    @OneToMany(mappedBy = "role", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<RolePermission> rolePermissions = new ArrayList<RolePermission>(0);

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Role() {
    }

    public Role create(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
        return this;
    }

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

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public List<OrganizationRole> getOrganizationRoles() {
        return organizationRoles;
    }

    public void setOrganizationRoles(List<OrganizationRole> organizationRoles) {
        this.organizationRoles = organizationRoles;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public RolePermission addRolePermission(RolePermission rolePermission) {
        rolePermission.setRole(this);
        this.getRolePermissions().add(rolePermission);
        return rolePermission;
    }

    public RolePermission removeRolePermission(RolePermission rolePermission) {
        rolePermission.setRole(null);
        this.getRolePermissions().remove(rolePermission);
        return rolePermission;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}