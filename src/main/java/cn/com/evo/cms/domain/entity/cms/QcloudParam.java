package cn.com.evo.cms.domain.entity.cms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cms_qcloud_param database table.
 * 
 */
@Entity
@Table(name="cms_qcloud_param")
@NamedQuery(name="QcloudParam.findAll", query="SELECT q FROM QcloudParam q")
public class QcloudParam extends com.frameworks.core.entity.AuthBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="app_id")
	private String appId;

	@Column(name="bucket_name")
	private String bucketName;

	private String cosregion;

	private Integer enable;

	private String remark;

	@Column(name="secret_id")
	private String secretId;

	@Column(name="secret_key")
	private String secretKey;

	public QcloudParam() {
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBucketName() {
		return this.bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getCosregion() {
		return this.cosregion;
	}

	public void setCosregion(String cosregion) {
		this.cosregion = cosregion;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSecretId() {
		return this.secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}