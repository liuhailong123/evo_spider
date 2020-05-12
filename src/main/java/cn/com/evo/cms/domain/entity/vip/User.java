package cn.com.evo.cms.domain.entity.vip;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the vip_user database table.
 * 
 */
@Entity
@Table(name = "vip_user")
@NamedQuery(name = "User.findAll", query = "SELECT a FROM User a")
public class User extends com.frameworks.core.entity.BaseEntity {
	private static final long serialVersionUID = 1L;

	private String name;
	@Column(name = "id_card")
	private String idCard;
	private Integer sex;
	private String brithday;
	private String height;
	private String weight;
	@Column(name = "head_img")
	private String headImg;
	private String nick;
	@Column(name = "wx_num")
	private String wxNum;
	@Column(name = "qq_num")
	private Integer qqNum;
	private String nation;
	@Column(name = "wb_num")
	private String wbNum;
	private String email;
	private Integer status;
	private String phone;
	private String openId;
	private String password;
	
	public User() {
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBrithday() {
		return brithday;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getWxNum() {
		return wxNum;
	}

	public void setWxNum(String wxNum) {
		this.wxNum = wxNum;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWbNum() {
		return wbNum;
	}

	public void setWbNum(String wbNum) {
		this.wbNum = wbNum;
	}


	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getQqNum() {
		return qqNum;
	}

	public void setQqNum(Integer qqNum) {
		this.qqNum = qqNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}