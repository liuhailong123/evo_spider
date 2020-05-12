package cn.com.evo.cms.domain.entity.cms;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="`cms_index_conf`")
@NamedQuery(name="IndexConf.findAll", query="SELECT f FROM IndexConf f")
public class IndexConf extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	private Integer count;

	private String imgUrl;

	private String info;

	private String name;

	private String appId;

	public IndexConf() {
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}