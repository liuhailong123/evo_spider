package cn.com.evo.cms.domain.entity.cms;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.frameworks.core.entity.BaseEntity;

@Entity
@Table(name = "cms_active_info")
@NamedQuery(name = "Active.findAll", query = "SELECT q FROM Active q")
public class Active extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String info;
	private String otherInfo;
	private String author;
	private String synopsis;
	private String sponsor;
	private String validTime;//生效时间
	private String unValidTime;//失效时间

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getUnValidTime() {
		return unValidTime;
	}

	public void setUnValidTime(String unValidTime) {
		this.unValidTime = unValidTime;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
