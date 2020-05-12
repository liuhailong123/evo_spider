package cn.com.evo.cms.domain.entity.pay;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 支付方式配置
 */
@Entity
@Table(name = "pay_config_info")
@NamedQuery(name = "PayConfig.findAll", query = "SELECT a FROM PayConfig a")
public class PayConfig extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer payType;
    private String remark;
    private Integer enable;
    private String provinceCode;

    public PayConfig() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}