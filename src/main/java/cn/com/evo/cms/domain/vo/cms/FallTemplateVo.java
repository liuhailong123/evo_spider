package cn.com.evo.cms.domain.vo.cms;

import com.frameworks.core.vo.BaseVo;

public class FallTemplateVo extends BaseVo{
	private static final long serialVersionUID = 1L;


	private Integer count;

	private String imgUrl;

	private String info;

	private String name;
	
	
	public FallTemplateVo() {
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}