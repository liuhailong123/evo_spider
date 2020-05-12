package cn.com.evo.admin.manage.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

/**
 * The persistent class for the sys_organization database table.
 * 
 */
@Entity
@Table(name = "sys_organization")
@NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o")
public class Organization extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 64)
    private String name;

    private int code;
    
    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int priority;

    @Column(length = 256)
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Organization parent;

    @OneToMany(mappedBy = "parent", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<Organization> children = Lists.newArrayList();

    @OneToMany(mappedBy = "organization", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<OrganizationRole> organizationRoles = Lists.newArrayList();

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    public Organization() {
    }

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

    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    public Organization addChildren(Organization children) {
        children.setParent(this);
        this.getChildren().add(children);
        return children;
    }

    public Organization removeChildren(Organization children) {
        children.setParent(null);
        this.getChildren().remove(children);
        return children;
    }

    public List<OrganizationRole> getOrganizationRoles() {
        return organizationRoles;
    }

    public void setOrganizationRoles(List<OrganizationRole> organizationRoles) {
        this.organizationRoles = organizationRoles;
    }

    public OrganizationRole addRole(OrganizationRole organizationRole) {
        organizationRole.setOrganization(this);
        this.getOrganizationRoles().add(organizationRole);
        return organizationRole;
    }

    public OrganizationRole removeRole(OrganizationRole organizationRole) {
        organizationRole.setOrganization(null);
        this.getOrganizationRoles().remove(organizationRole);
        return organizationRole;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}