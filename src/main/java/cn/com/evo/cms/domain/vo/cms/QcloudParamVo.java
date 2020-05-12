package cn.com.evo.cms.domain.vo.cms;

import java.io.Serializable;

public class QcloudParamVo extends com.frameworks.core.vo.AuthBaseVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String appId;
	private String bucketName;
	private String cosregion;
	private Integer enable;
	private String remark;
	private String secretId;
	private String secretKey;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getCosregion() {
		return cosregion;
	}

	public void setCosregion(String cosregion) {
		this.cosregion = cosregion;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}