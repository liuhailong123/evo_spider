package cn.com.evo.cms.domain.vo.cms;

import com.frameworks.core.vo.BaseVo;


public class IndexConfVo extends BaseVo {
	private static final long serialVersionUID = 1L;

	private Integer count;

	private String imgUrl;

	private String info;

	private String name;

	private String appId;

	public IndexConfVo() {
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}