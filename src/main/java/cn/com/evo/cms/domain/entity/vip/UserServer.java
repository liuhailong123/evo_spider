package cn.com.evo.cms.domain.entity.vip;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigInteger;


/**
 * 用户服务开通情况表-对应实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "vip_user_server")
@NamedQuery(name = "UserServer.findAll", query = "SELECT v FROM UserServer v")
public class UserServer extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

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

    public UserServer(String userId, String appId, String serverCode, String maturityTime) {
        this.userId = userId;
        this.appId = appId;
        this.serverCode = serverCode;
        this.maturityTime = maturityTime;
    }
}