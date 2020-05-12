package cn.com.evo.cms.domain.entity.pay;

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

import cn.com.evo.admin.manage.domain.entity.Area;

/**
 * 产品渠道表
 * @author shilinxiao
 *
 */
@Entity
@Table(name = "pay_product_channel")
@NamedQuery(name = "ProductChannel.findAll", query = "SELECT o FROM ProductChannel o")
public class ProductChannel extends com.frameworks.core.entity.BaseEntity {
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
    private ProductChannel parent;

    @OneToMany(mappedBy = "parent", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<ProductChannel> children = Lists.newArrayList();

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    public ProductChannel() {
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

    public ProductChannel getParent() {
        return parent;
    }

    public void setParent(ProductChannel parent) {
        this.parent = parent;
    }

    public List<ProductChannel> getChildren() {
        return children;
    }

    public void setChildren(List<ProductChannel> children) {
        this.children = children;
    }

    public ProductChannel addChildren(ProductChannel children) {
        children.setParent(this);
        this.getChildren().add(children);
        return children;
    }

    public ProductChannel removeChildren(ProductChannel children) {
        children.setParent(null);
        this.getChildren().remove(children);
        return children;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}