package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the cms_column database table.
 */
@Entity
@Table(name = "cms_column")
@NamedQuery(name = "Column.findAll", query = "SELECT c FROM Column c")
public class Column extends com.frameworks.core.entity.BaseEntityAutoIncrement {
    private static final long serialVersionUID = 1L;

    private String name;

    private String templateCode;

    private String title;

    private String columnCode;

    private String thirdCode;

    private int level;

    private int type;

    @javax.persistence.Column(columnDefinition = "varchar(2048) COMMENT '名称全拼'")
    private String nameSpellLong;

    @javax.persistence.Column(columnDefinition = "varchar(2048) COMMENT '名称简拼'")
    private String nameSpellShort;

    @javax.persistence.Column(columnDefinition = "int(2) COMMENT '是否启用'")
    private int enable;

    //bi-directional many-to-one association to Column
    @ManyToOne
    @JoinColumn(name = "pId")
    private Column parent;

    @javax.persistence.Column(columnDefinition = "int(11) COMMENT '排序'")
    private int sort;

    @javax.persistence.Column(columnDefinition = "varchar(2048) COMMENT '分类标签'")
    private String classifyTags;

    //bi-directional many-to-one association to Column
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private List<Column> children = new ArrayList<Column>(0);

    private int isRecommend;

    private String platform;

    public Column() {
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getClassifyTags() {
        return classifyTags;
    }

    public void setClassifyTags(String classifyTags) {
        this.classifyTags = classifyTags;
    }

    public Column(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public String getColumnCode() {
        return columnCode;
    }


    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Column getParent() {
        return parent;
    }

    public void setParent(Column parent) {
        this.parent = parent;
    }

    public List<Column> getChildren() {
        return children;
    }

    public void setChildren(List<Column> children) {
        this.children = children;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getNameSpellLong() {
        return nameSpellLong;
    }

    public void setNameSpellLong(String nameSpellLong) {
        this.nameSpellLong = nameSpellLong;
    }

    public String getNameSpellShort() {
        return nameSpellShort;
    }

    public void setNameSpellShort(String nameSpellShort) {
        this.nameSpellShort = nameSpellShort;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}