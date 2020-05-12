package com.frameworks.core.shiro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.com.evo.admin.manage.domain.entity.Account;


/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {

	private static final long serialVersionUID = -1748602382963711884L;
	private String id;
	private String loginName;
	private String ipAddress;
	private Account account;
		
	/**
	 * 加入更多的自定义参数
	 */
	private Map<String, Object> attribute = new HashMap<String, Object>();
	
	public ShiroUser() {
		
	}
	
	public ShiroUser(String loginName) {
		this.loginName = loginName;
	}
	
	public ShiroUser(String id, String loginName) {
		this.id = id;
		this.loginName = loginName;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void setAttribute(String name, Object value) {
		attribute.put(name, value);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Object getAttribute(String name) {
		return attribute.get(name);
	}
	
	public Object removeAttribute(String name) {
		return attribute.remove(name);
	}
	
	public Map<String, Object> getAttributes() {
		return this.attribute;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}
}