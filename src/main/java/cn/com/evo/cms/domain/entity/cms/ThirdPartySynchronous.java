package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 三方数据同步表 -与歌华同步数据时 创建的
 * 日后与其他 三方同步数据时 也可以使用
 * The persistent class for the cms_third_party_synchronous database table.
 * 
 */
@Entity
@Table(name = "cms_third_party_synchronous")
@NamedQuery(name = "ThirdPartySynchronous.findAll", query = "SELECT t FROM ThirdPartySynchronous t")
public class ThirdPartySynchronous extends com.frameworks.core.entity.BaseEntity {
	private static final long serialVersionUID = 1L;

	private String fid;//外键

	private String thirdPartyName;//三方名称 （歌华/.../...）

	private String type;//类型 1 栏目 2 电影 3 剧集

	private String retCode;//同步结果状态码

	private String retMsg;//同步结果状态描述

	public ThirdPartySynchronous() {
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getFid() {
		return this.fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getThirdPartyName() {
		return this.thirdPartyName;
	}

	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}