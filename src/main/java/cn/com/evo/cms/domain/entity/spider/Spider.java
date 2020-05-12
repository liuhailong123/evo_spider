package cn.com.evo.cms.domain.entity.spider;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author rf
 * @date 2020/4/28
 * 爬虫配置表
 */
@Entity
@Table(name = "spi_spider")
@NamedQuery(name = "Spider.findAll", query = "SELECT s FROM Spider s")
public class Spider extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private String bizId;

    private String bizName;

    private String spiderUrl;

    private Integer type;

    private String remark;

    public Spider() {
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getSpiderUrl() {
        return spiderUrl;
    }

    public void setSpiderUrl(String spiderUrl) {
        this.spiderUrl = spiderUrl;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bizId", bizId)
                .append("bizName", bizName)
                .append("spiderUrl", spiderUrl)
                .append("type", type)
                .append("remark", remark)
                .toString();
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
