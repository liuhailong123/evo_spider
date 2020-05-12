package cn.com.evo.cms.domain.entity.pay;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 支付方式参数配置
 */
@Entity
@Table(name = "pay_config_params")
@NamedQuery(name = "PayConfigParams.findAll", query = "SELECT a FROM PayConfigParams a")
public class PayConfigParams extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private String configId;
    private String nameEn;
    private String nameCn;
    private String value;
    private Integer sort;
    private String remark;

    public PayConfigParams() {
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}