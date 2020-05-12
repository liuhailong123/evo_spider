package cn.com.evo.cms.domain.entity.vip;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the vip_verification_code database table.
 * 
 */
@Entity
@Table(name="vip_verification_code")
@NamedQuery(name="VerificationCode.findAll", query="SELECT v FROM VerificationCode v")
public class VerificationCode extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	private String code;

	private BigInteger invalid;//失效时间

	private String phone;

	public VerificationCode() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigInteger getInvalid() {
		return this.invalid;
	}

	public void setInvalid(BigInteger invalid) {
		this.invalid = invalid;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}