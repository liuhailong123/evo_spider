package cn.com.evo.cms.domain.entity.vip;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the vip_user database table.
 */
@Entity
@Table(name = "vip_user_account")
@NamedQuery(name = "UserAccount.findAll", query = "SELECT a FROM UserAccount a")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccount extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private String userId;
    /**
     * 账号类型 1 手机号; 2 微信号; 3 机顶盒卡号
     */
    private Integer accountType;
    /**
     * 账号
     */
    private String accountNo;
    /**
     * 设备名称
     */
    private String equipmentName;
    /**
     * 设备标识
     */
    private String equipmentId;
    /**
     * 设备类型 1 手机; 2 机顶盒; 3 OTT
     */
    private Integer equipmentType;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 渠道编码
     */
    private String channelCode;

}