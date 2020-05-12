package cn.com.evo.cms.domain.entity.vip;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "vip_user_lock_record")
@NamedQuery(name = "UserLockRecord.findAll", query = "SELECT a FROM UserLockRecord a")
public class UserLockRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) COMMENT '用户id'")
    private String userId;

    @Column(columnDefinition = "varchar(32) COMMENT '应用加锁配置Id'")
    private String appLockConfId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppLockConfId() {
        return appLockConfId;
    }

    public void setAppLockConfId(String appLockConfId) {
        this.appLockConfId = appLockConfId;
    }
}
