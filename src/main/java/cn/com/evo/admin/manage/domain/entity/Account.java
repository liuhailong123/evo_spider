package cn.com.evo.admin.manage.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sys_account")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(length = 20)
    private String username;

    @Transient
    private String plainPassword;

    @Column(length = 40)
    private String password;

    @Column(length = 50)
    private String realname;
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 16)
    private String mobile;

    private int status;

    private int superman;

    @Column(length = 16)
    private String salt;

    @OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    private List<AccountRole> accountRoles = new ArrayList<AccountRole>(0);

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Account() {
    }

    public Account(String username, String password, String realname, String email, String mobile, int status,
            int superman) {
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.superman = superman;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSuperman() {
        return superman;
    }

    public void setSuperman(int superman) {
        this.superman = superman;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }
}