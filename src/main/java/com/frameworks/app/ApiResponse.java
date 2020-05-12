package com.frameworks.app;

import java.io.Serializable;

public class ApiResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Status status = Status.OK; //发生异常

	private int retCode = 0; // 业务码

	private String retMsg = ""; // 默认空内容;

	private Integer total = null; // 默认Null JSON将不输出

	private Object rows = null; // 默认Null JSON将不输出

	public ApiResponse() {
	}

	public ApiResponse(Status status, int retCode, String retMsg) {
		this(status, retCode, retMsg, null, null);
	}

	public ApiResponse(Status status, int retCode, String retMsg, Integer total, Object rows) {
		this.status = status;
		this.retCode = retCode;
		this.retMsg = retMsg;
		this.total = total;
		this.rows = rows;
	}

	public ApiResponse pushOk(String retMsg) {
		this.pushOk(retMsg, null, null);
		return this;
	}
	
	public ApiResponse pushOk(String message, Integer total, Object rows) {
		this.status = Status.OK;
		this.retCode = 0;
		this.retMsg = message;
		this.total = total;
		this.rows = rows;
		return this;
	}
	
	public ApiResponse pushError(Integer retCode, String retMsg) {
		this.status = Status.ERROR;
		this.retCode = retCode;
		this.retMsg = retMsg;
		return this;
	}
	
	public ApiResponse pushTimeout(Integer retCode, String retMsg) {
		this.status = Status.TIMEOUT;
		this.retCode = retCode;
		this.retMsg = retMsg;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public static enum Status {
		// 成功
		OK,
		// 失败
		ERROR,
		// 超时
		TIMEOUT;
	}

}
