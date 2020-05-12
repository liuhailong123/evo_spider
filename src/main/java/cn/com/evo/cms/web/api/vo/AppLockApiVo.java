package cn.com.evo.cms.web.api.vo;

import com.frameworks.core.vo.BaseVo;

public class AppLockApiVo {
	private static final long serialVersionUID = 1L;

	private String id;

	private String appId;

	private String contentId;

	private  String contentName;

	private Integer contentType;

	private Integer status;

	public AppLockApiVo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
