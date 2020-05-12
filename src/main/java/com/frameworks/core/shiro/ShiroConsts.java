package com.frameworks.core.shiro;

public interface ShiroConsts {
	/**
	 * 登录用户
	 */
	public final static String LOGIN_USER = "login_user";    
	
	/**
	 * 验证码
	 */
	public final static String CAPTCHA_KEY = "captcha_key";
	
	/**
	 * 验证码
	 */
	public final static int SUPER_MAN = 0;
	
	/**
	 * 验证码
	 */
	public final static int ADMINISTRATOR = 0;
	
	/**
	 * 默认菜单权限编码
	 */
	public final static String PERMISSION_SHOW_DEFAULT_CODE = "show";
	public final static String PERMISSION_ADD_DEFAULT_CODE = "add";
	public final static String PERMISSION_MODIFY_DEFAULT_CODE = "modify";
	public final static String PERMISSION_REMOVE_DEFAULT_CODE = "remove";
	public final static String PERMISSION_SEARCH_DEFAULT_CODE = "search";
}
