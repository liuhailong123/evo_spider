package com.frameworks.core.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础VO
 * 
 * @author LUXIN
 */
public class BaseVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Date createDate;
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

}
