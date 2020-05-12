package cn.com.evo.cms.domain.entity.vip;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the vip_message_param database table.
 * 
 */
@Entity
@Table(name="vip_message_param")
@NamedQuery(name="MessageParam.findAll", query="SELECT v FROM MessageParam v")
public class MessageParam extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	private String message;

	private String password;

	private String sn;

	private int status;

	public MessageParam() {
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}