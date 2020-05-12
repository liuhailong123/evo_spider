package cn.com.evo.cms.domain.vo.vip;

import com.frameworks.core.vo.BaseVo;

public class UserLockRecordVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String appLockConfId;

    private String lockContentName;

    private Integer lockContentType;

    public String getLockContentName() {
        return lockContentName;
    }

    public void setLockContentName(String lockContentName) {
        this.lockContentName = lockContentName;
    }

    public Integer getLockContentType() {
        return lockContentType;
    }

    public void setLockContentType(Integer lockContentType) {
        this.lockContentType = lockContentType;
    }

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
