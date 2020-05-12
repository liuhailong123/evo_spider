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

import com.frameworks.core.entity.BaseEntity;
import com.google.common.collect.Lists;

/**
 * The persistent class for the sys_area database table.
 * 
 */
@Entity
@Table(name = "sys_areas")
@NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
public class Area extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String name;
    private String shortName;
    private String jd;
    private String wd;
    private int level;
    @Column(name = "sort")
    private int priority;
    
    @ManyToOne
    @JoinColumn(name = "pid")
    private Area parent;

    @OneToMany(mappedBy = "parent", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<Area> children = Lists.newArrayList();

    public Area() {
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }

    public List<Area> getChildren() {
        return children;
    }

    public void setChildren(List<Area> children) {
        this.children = children;
    }

    public Area addChildren(Area children) {
        children.setParent(this);
        this.getChildren().add(children);
        return children;
    }

    public Area removeChildren(Area children) {
        children.setParent(null);
        this.getChildren().remove(children);
        return children;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }
}