package com.frameworks.core.web.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @FileName : DataResultForAPI.java
 * @Description :
 * @Copyright : Copyright (c) 2017
 * @author lu.xin
 * @date 2017年06月07日17:44:39
 */
public class DataResultForAPI extends MsgResult {

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		JSON.DEFFAULT_DATE_FORMAT = DEFAULT_DATE_FORMAT;
		this.data = data;
	}
}
