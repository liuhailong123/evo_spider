package com.frameworks.core.web.result;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @FileName : MsgResult.java
 * @Description : 
 * @Copyright : Copyright (c) 2015
 * @author caoyong
 * @date Jul 29, 2015 12:23:47 AM
 */
public class MsgResult implements Result{
	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 1L;

	private Status status;

	private String message;

	private Map<String, Object> extra = null;

	public void pushOk(String message){
		this.status = Status.OK;
		this.message = message;
	}
	
	public void pushError(String message){
		this.status = Status.ERROR;
		this.message = message;
	}
	
	public void pushInvalid(String message){
		this.status = Status.INVALID;
		this.message = message;
	}
	
	public void pushTimeout(String message){
		this.status = Status.TIMEOUT;
		this.message = message;
	}
	
	public void pushConfirm(String message){
		this.status = Status.CONFIRM;
		this.message = message;
	}
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Map<String, Object> getExtra() {
		if(extra == null){
			extra = Maps.newHashMap();
		}
		return extra;
	}

	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}
}
