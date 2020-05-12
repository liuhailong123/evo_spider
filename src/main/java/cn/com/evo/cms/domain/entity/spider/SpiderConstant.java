package cn.com.evo.cms.domain.entity.spider;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author rf
 * @date 2020/4/28
 * 爬虫配置子表实体
 */
@Entity
@Table(name = "spi_spider_constant")
@NamedQuery(name = "SpiderConstant.findAll", query = "SELECT s FROM SpiderConstant s")
public class SpiderConstant extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * key
     */
    private String constantKey;

    /**
     * 值
     */
    private String constantValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否启用
     */
    private Integer enable;

    public SpiderConstant() {
    }

    public String getConstantKey() {
        return constantKey;
    }

    public void setConstantKey(String constantKey) {
        this.constantKey = constantKey;
    }

    public String getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("constantKey", constantKey)
                .append("constantValue", constantValue)
                .append("remark", remark)
                .append("enable", enable)
                .toString();
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
