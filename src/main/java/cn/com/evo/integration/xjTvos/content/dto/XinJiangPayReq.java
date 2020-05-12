package cn.com.evo.integration.xjTvos.content.dto;

import java.io.Serializable;

public class XinJiangPayReq implements Serializable {

	private static final long serialVersionUID = 1L;
	private String authCode;// 应用授权编码
	private String cardNo;// 智能卡号
	private String stbNo;// 机顶盒号
	private String stbIp;// 机顶盒ip
	private Integer bookId;// 账本编号
	private String prodCode;// 产品编码
	private String contentCode;// 产品内容编码
	private String backUrl;// 结果通知地址
	private String mobile;// 手机号

	public XinJiangPayReq(String authCode, String cardNo, String stbNo, String stbIp, Integer bookId, String prodCode,
			String contentCode, String backUrl, String mobile) {
		this.authCode = authCode;
		this.cardNo = cardNo;
		this.stbNo = stbNo;
		this.stbIp = stbIp;
		this.bookId = bookId;
		this.prodCode = prodCode;
		this.contentCode = contentCode;
		this.backUrl = backUrl;
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getStbNo() {
		return stbNo;
	}

	public void setStbNo(String stbNo) {
		this.stbNo = stbNo;
	}

	public String getStbIp() {
		return stbIp;
	}

	public void setStbIp(String stbIp) {
		this.stbIp = stbIp;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getContentCode() {
		return contentCode;
	}

	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

}
