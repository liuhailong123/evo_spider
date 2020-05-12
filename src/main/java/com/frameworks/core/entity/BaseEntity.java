package com.frameworks.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.frameworks.core.web.constants.WebConsts;

/**
 * JPA 统一实体基类
 * 
 * @author cao.yong
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "id", columnDefinition = "")
	
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = WebConsts.DEFAULT_DATETIME_FORMAT)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = WebConsts.DEFAULT_DATETIME_FORMAT)
	private Date modifyDate;
	
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

	@PrePersist
	public void onCreate() {
		this.createDate = this.modifyDate = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		this.modifyDate = new Date();
	}
}
