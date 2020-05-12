package cn.com.evo.cms.domain.vo.total;

import cn.com.evo.cms.domain.vo.cms.ContentVo;

/**
 * The persistent class for the cms_content database table.
 */
public class ContentOperationVo extends com.frameworks.core.vo.BaseVo {

    /**
     * 内容id
     */
    private ContentVo content;

    /**
     * 相关性id
     * 操作id
     */
    private String correlateId;

    /**
     * 其他信息
     */
    private String info;
    /**
     * 状态 1-注入中；2-注入成功;3-注入失败
     */
    private Integer status;

    public ContentVo getContent() {
        return content;
    }

    public void setContent(ContentVo content) {
        this.content = content;
    }

    public String getCorrelateId() {
        return correlateId;
    }

    public void setCorrelateId(String correlateId) {
        this.correlateId = correlateId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}