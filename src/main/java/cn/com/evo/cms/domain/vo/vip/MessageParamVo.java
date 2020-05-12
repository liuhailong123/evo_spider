package cn.com.evo.cms.domain.vo.vip;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信平台参数配置
 * @author shilinxiao
 */
public class MessageParamVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String message;
	private String password;
	private String sn;
	private int status;
	private Date createDate;
	private Date modifyDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}