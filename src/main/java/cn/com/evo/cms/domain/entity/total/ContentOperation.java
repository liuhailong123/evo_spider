package cn.com.evo.cms.domain.entity.total;

import cn.com.evo.cms.domain.entity.cms.Content;
import org.springframework.web.servlet.support.BindStatus;

import javax.persistence.*;

/**
 * The persistent class for the cms_content database table.
 */
@Entity
@Table(name = "cms_content_operation")
@NamedQuery(name = "ContentOperation.findAll", query = "SELECT c FROM ContentOperation c")
public class ContentOperation extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 内容id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contentId")
    private Content content;

    /**
     * 相关性id
     * 操作id
     */
    private String correlateId;

    /**
     * 其他信息
     */
    @Column(columnDefinition = "text comment '其他信息'")
    private String info;

    /**
     * 状态 1-注入中；2-注入成功;3-注入失败
     */
    private Integer status;

    /**
     * 定价状态  0-定价成功；1-定价失败
     */
    private Integer bindStatus;

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getCorrelateId() {
        return correlateId;
    }

    public void setCorrelateId(String correlateId) {
        this.correlateId = correlateId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}