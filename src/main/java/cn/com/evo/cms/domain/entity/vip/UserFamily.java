package cn.com.evo.cms.domain.entity.vip;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the vip_user database table.
 */
@Entity
@Table(name = "vip_user_family")
@NamedQuery(name = "UserFamily.findAll", query = "SELECT a FROM UserFamily a")
public class UserFamily extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 关系类型；1-子女；2-父母；3-其他
     */
    private Integer type;
    /**
     * 性别；1-男；2-女；0-保密
     */
    private Integer sex;
    /**
     * 生日
     */
    private String birthday;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}