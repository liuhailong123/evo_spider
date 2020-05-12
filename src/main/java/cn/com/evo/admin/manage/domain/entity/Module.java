package cn.com.evo.admin.manage.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * The persistent class for the sys_module database table.
 * 
 */
@Entity
@Table(name = "sys_module")
@NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m")
public class Module extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, length = 60)
    private String code;

    @Column(nullable = false, length = 200)
    private String icon;

    @Column(nullable = false, length = 225)
    private String url;

    @Column(nullable = false)
    private int priority;

    @Column(length = 45)
    private int status;

    @Column(length = 225)
    private String description;

    @Column(nullable = false)
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Module parent;

    @OneToMany(mappedBy = "parent", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    @OrderBy("priority ASC")
    private List<Module> children = new ArrayList<Module>(0);

    @OneToMany(mappedBy = "module", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<Permission> permissions = new ArrayList<Permission>(0);

    public Module() {
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

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public List<Module> getChildren() {
        return children;
    }

    public void setChildren(List<Module> children) {
        this.children = children;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Permission addPermission(Permission permission) {
        permission.setModule(this);
        this.getPermissions().add(permission);
        return permission;
    }

    public Permission removePermission(Permission permission) {
        permission.setModule(null);
        this.getPermissions().remove(permission);
        return permission;
    }
}