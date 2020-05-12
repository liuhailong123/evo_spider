package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class AccountVo extends BaseVo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String realname;

    private String email;

    private String mobile;

    private int status;

    private int superman;

    private OrganizationVo organization;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public OrganizationVo getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationVo organization) {
        this.organization = organization;
    }

}
