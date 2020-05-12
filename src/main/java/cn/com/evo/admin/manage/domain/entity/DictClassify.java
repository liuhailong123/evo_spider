package cn.com.evo.admin.manage.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the sys_dict_classify database table.
 * 
 */
@Entity
@Table(name = "sys_dict_classify")
@NamedQuery(name = "DictClassify.findAll", query = "SELECT d FROM DictClassify d")
public class DictClassify extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 60)
    private String code;

    @Column(length = 225)
    private String description;

    @OneToMany(mappedBy = "dictClassify", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    private List<DictData> dictData = new ArrayList<DictData>(0);

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

    public List<DictData> getDictData() {
        return dictData;
    }

    public void setDictData(List<DictData> dictData) {
        this.dictData = dictData;
    }

}