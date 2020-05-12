package cn.com.evo.admin.manage.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sys_organization_role database table.
 * 
 */
@Entity
@Table(name = "sys_organization_role")
@NamedQuery(name = "OrganizationRole.findAll", query = "SELECT o FROM OrganizationRole o")
public class OrganizationRole extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public OrganizationRole() {
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}