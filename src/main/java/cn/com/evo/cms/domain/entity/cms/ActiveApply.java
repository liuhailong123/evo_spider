package cn.com.evo.cms.domain.entity.cms;

import com.frameworks.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 活动报名表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cms_active_apply")
@NamedQuery(name = "ActiveApply.findAll", query = "SELECT a FROM ActiveApply a")
public class ActiveApply extends BaseEntity {
    private static final long serialVersionUID = 1L;
    //  卡号 手机号
    @Column(columnDefinition = "varchar(32) comment '活动ID'")
    private String activeId;

    @Column(columnDefinition = "varchar(32) comment '用户Id'")
    private String userId;

    @Column(columnDefinition = "varchar(32) comment '用户账户号'")
    private String userAccountNo;

    @Column(columnDefinition = "varchar(11) comment '报名手机号'")
    private String phone;
}
