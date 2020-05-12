package com.frameworks.core.web.result;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 验证器消息
 * @author caoyong
 *
 */
public class ValidResult extends MsgResult {
	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidResult() {
		super.pushInvalid("输入有误");
	}
	
	private Map<String, String> errors;

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public Map<String, String> getErrors() {
		if(errors == null){
			errors = Maps.newHashMap();
		}
		return errors;
	}
	
	public void putErrors(String errorKey, String errorMsg){
		getErrors().put(errorKey, errorMsg);
	}
	

}
