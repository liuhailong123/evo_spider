package cn.com.evo.cms.domain.entity.app;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cms_app_lock_conf")
@NamedQuery(name = "AppLockConf.findAll", query = "SELECT a FROM AppLockConf a")
public class AppLockConf extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) COMMENT '应用Id'")
    private String appId;

    @Column(columnDefinition = "varchar(32) COMMENT '加锁内容Id'")
    private String contentId;

    @Column(columnDefinition = "int(2) COMMENT '加锁内容类型'")
    private Integer contentType;

    @Column(columnDefinition = "int(2) COMMENT '是否默认加锁'")
    private Integer status;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
