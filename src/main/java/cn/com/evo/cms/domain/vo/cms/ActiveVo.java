package cn.com.evo.cms.domain.vo.cms;

import com.frameworks.core.vo.BaseVo;

public class ActiveVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String name;
	private String info;
	private String author;
	private String synopsis;
	private String sponsor;
	private String validTime;
	private String unValidTime;
	private String imgUrl;
	private String imgName;

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

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
