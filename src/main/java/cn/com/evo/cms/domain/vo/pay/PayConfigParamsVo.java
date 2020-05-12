package cn.com.evo.cms.domain.vo.pay;

/**
 * 支付方式配置
 */
public class PayConfigParamsVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String configId;
    private String nameEn;
    private String nameCn;
    private String value;
    private Integer sort;
    private String remark;

    public PayConfigParamsVo() {
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