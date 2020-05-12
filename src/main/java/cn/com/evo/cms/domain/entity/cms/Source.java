package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cms_source database table.
 */
@Entity
@Table(name = "cms_source")
@NamedQuery(name = "Source.findAll", query = "SELECT s FROM Source s")
public class Source extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;

    private String remark;

    /**
     * 华数省网，该字段存储 内容类型
     */
    private String info;

    private int type;

    public Source() {
    }

    public static Source createPicture(String name){
        Source entity = new Source();
        entity.setName(name);
        entity.setType(2);
        return entity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}