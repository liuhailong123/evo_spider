package cn.com.evo.cms.domain.vo.spider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author rf
 * @date 2020/4/28
 * 爬虫配置表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpiderVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String bizId;

    private String bizName;

    private String spiderUrl;

    private Integer type;

    private String remark;

    public SpiderVo() {
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
}
