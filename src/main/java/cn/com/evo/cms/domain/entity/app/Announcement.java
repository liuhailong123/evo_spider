package cn.com.evo.cms.domain.entity.app;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the cms_announcement database table.
 * 
 */
@Entity
@Table(name = "cms_announcement")
@NamedQuery(name = "Announcement.findAll", query = "SELECT q FROM Announcement q")
public class Announcement extends com.frameworks.core.entity.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Integer type;
	private String content;
	private String remark;
	private String startTime;
	private String endTime;
	private Integer status;
	private String blankUrl;
	public Announcement() {
	}

	public Announcement(String name, String content,String remark) {
		this.name=name;
		this.content=content;
		this.remark=remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBlankUrl() {
		return blankUrl;
	}

	public void setBlankUrl(String blankUrl) {
		this.blankUrl = blankUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}