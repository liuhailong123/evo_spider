package cn.com.evo.admin.manage.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sys_dict_data")
@NamedQuery(name = "DictData.findAll", query = "SELECT d FROM DictData d")
public class DictData extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 60)
    private String code;

    @Column(nullable = false)
    private int priority;

    @ManyToOne
    @JoinColumn(name = "classify_id", nullable = false)
    private DictClassify dictClassify;

    public DictData() {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public DictClassify getDictClassify() {
        return dictClassify;
    }

    public void setDictClassify(DictClassify dictClassify) {
        this.dictClassify = dictClassify;
    }
}