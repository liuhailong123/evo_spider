package com.frameworks.core.web.constants;

public interface WebConsts {
	/**
	 * 日志参数
	 */
	public final static String LOG_ARGUMENTS = "log_arguments";
	
	/**
	 * 动态查询，参数前缀
	 */
	public final static String SEARCH_PREFIX = "search_";
	
	/**
	 * 内部动态查询参数常量
	 */
	public final static String NEST_DYNAMIC_SEARCH = "$nest_dynamic_search$";
	
	/**
	 * 内部动态查询参数常量，logical
	 */
	public final static String NEST_DYNAMIC_SEARCH_LOGICAL = "$nest_dynamic_search_logical$";
	
	/**
	 * 默认根编码，不允许删除
	 */
	public final static int DEFAULT_ROOT_LEVEL = 0;
	
	public final static String DEFAULT_ENCODING = "UTF-8";
	
	public final static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public final static String DEFAULT_TIME_FORMAT = "HH:mm:ss";
}
