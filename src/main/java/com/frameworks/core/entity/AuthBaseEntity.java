package com.frameworks.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.frameworks.core.shiro.ShiroUser;
import com.frameworks.core.web.constants.WebConsts;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.Organization;

/**
 * JPA 统一实体基类（数据权限）
 * 
 * @author lu.xin
 */
@MappedSuperclass
public class AuthBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", columnDefinition = "")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = WebConsts.DEFAULT_DATETIME_FORMAT)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = WebConsts.DEFAULT_DATETIME_FORMAT)
	private Date modifyDate;

	@Column(name = "authId")
	private String authId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	@PrePersist
	public void onCreate() {
		this.createDate = this.modifyDate = new Date();
		this.authId = this.getOrganizationId();
	}

	@PreUpdate
	public void onUpdate() {
		this.modifyDate = new Date();
		this.authId = this.getOrganizationId();
	}

	protected Account getLoginUser() {
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null){
			ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
			if (shiroUser == null) {
				return null;
			} else {
				return shiroUser.getAccount();
			}
		}else{
			return null;
		}
	}

	protected String getOrganizationId() {
		if (this.getLoginUser() != null) {
			if (this.getLoginUser().getOrganization() == null) {
//				return null;
				return "7";
			} else {
				Organization org = this.getLoginUser().getOrganization();
				int level = org.getLevel();
				while (level != 0) {// 循环拿到顶层机构id
					org = org.getParent();
					level--;
				}
				return org.getId();
			}
		} else {
//			return null;
			return "7";
		}
	}
}
