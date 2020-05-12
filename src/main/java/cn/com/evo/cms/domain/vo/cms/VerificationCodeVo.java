package cn.com.evo.cms.domain.vo.cms;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author Cao.Yong
 *
 */
public class VerificationCodeVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String code;
	private BigInteger invalid;// 失效时间
	private String phone;
	private Date createDate;
	private Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigInteger getInvalid() {
		return invalid;
	}

	public void setInvalid(BigInteger invalid) {
		this.invalid = invalid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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