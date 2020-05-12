package cn.com.evo.cms.domain.entity.vip;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 家长中心表
 */
@Entity
@Table(name = "vip_patriarch")
@NamedQuery(name = "Patriarch.findAll", query = "SELECT a FROM Patriarch a")
public class Patriarch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) COMMENT '用户id'")
    private String userId;

    @Column(columnDefinition = "varchar(256) COMMENT '用户名称'")
    private String userName;

    @Column(columnDefinition = "varchar(64) COMMENT '用户账号'")
    private String userAccountNo;

    @Column(columnDefinition = "varchar(20) COMMENT '家长密码'")
    private String patriarchPassWord;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccountNo() {
        return userAccountNo;
    }

    public void setUserAccountNo(String userAccountNo) {
        this.userAccountNo = userAccountNo;
    }

    public String getPatriarchPassWord() {
        return patriarchPassWord;
    }

    public void setPatriarchPassWord(String patriarchPassWord) {
        this.patriarchPassWord = patriarchPassWord;
    }
}
