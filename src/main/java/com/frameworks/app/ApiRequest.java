package com.frameworks.app;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ApiRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String params;

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public JSONObject toJSON(){
		return JSON.parseObject(this.params);
	}
	
	public String getParaString(String key){
		return toJSON().getString(key);
	}
	
	public Integer getParaInteger(String key){
		return toJSON().getInteger(key);
	}
	
	public Long getParaLong(String key){
		return toJSON().getLong(key);
	}
	
	public Date getParaDate(String key){
		return toJSON().getDate(key);
	}
}
