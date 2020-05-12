package com.frameworks.core.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.frameworks.core.shiro.ShiroUser;

import cn.com.evo.admin.manage.domain.entity.Account;

public class BaseController {

	protected Logger logger = LogManager.getLogger(this.getClass());
	
	protected Account getLoginUser(){
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		return shiroUser.getAccount();
	}
}
