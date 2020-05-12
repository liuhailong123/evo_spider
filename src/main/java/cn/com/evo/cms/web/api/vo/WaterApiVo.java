package cn.com.evo.cms.web.api.vo;

import java.io.Serializable;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.cms.Content;

public class WaterApiVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String hPicUrl;
	private String sPicUrl;
	private int sort;
	private int type;
	private int positionX;
	private int positionY;

	public WaterApiVo(Column column) {
		this.id=column.getId();
		this.name=column.getName();
		this.type=column.getType();
		this.sort=column.getSort();
	}

	public WaterApiVo(CatalogueRelation catalogueRelation, Content content) {
		this.id=content.getId();
		this.name=content.getName();
		this.sort=catalogueRelation.getSort();
		this.type=0;//内容
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String gethPicUrl() {
		return hPicUrl;
	}

	public void sethPicUrl(String hPicUrl) {
		this.hPicUrl = hPicUrl;
	}

	public String getsPicUrl() {
		return sPicUrl;
	}

	public void setsPicUrl(String sPicUrl) {
		this.sPicUrl = sPicUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

}
