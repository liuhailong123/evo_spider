package cn.com.evo.admin.manage.domain.entity;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @Author 陆鑫
 * @Description
 * @Date: Created in 17:01 20/8/18
 * @Modified By: 省网基本信息维护
 */
@Entity
@Table(name = "sys_province")
@NamedQuery(name = "Province.findAll", query = "SELECT p FROM Province p")
public class Province extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    private String name;

    /**
     * 启用状态(0:未启用 1:已启用)
     */
    private Integer enable;

    /**
     * 省网编码(此参数主要是用来在推送内容到ftp服务器时请求数据的传参)
     */
    private String code;

    /**
     * ftp服务器地址
     */
    private String ftpUrl;
    /**
     * ftp服务器用户名
     */
    private String ftpUser;
    /**
     * ftp服务器密码
     */
    private String ftpPassword;
    /**
     * ftp访问域名
     */
    private String ftpHost;

    private String imageHost;

    /**
     * ftp端口
     */
    private String ftpPort;

    public Province() {
    }

    public Province(String name, Integer enable, String code) {
        this.name = name;
        this.enable = enable;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFtpUrl() {
        return ftpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }
}
