package cn.com.evo.cms.domain.vo.vip;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户服务开通情况表-对应实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserServerVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户卡号
     */
    private String userAccount;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 服务编码
     */
    private String serverCode;

    /**
     * 到期时间
     */
    private String maturityTime;

    public UserServerVo() {
    }

    public UserServerVo(String userId, String appId, String serverCode, String maturityTime) {
        this.userId = userId;
        this.appId = appId;
        this.serverCode = serverCode;
        this.maturityTime = maturityTime;
    }
}