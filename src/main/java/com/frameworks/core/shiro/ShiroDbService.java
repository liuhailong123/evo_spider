package com.frameworks.core.shiro;

import java.util.Collection;
import java.util.List;

import cn.com.evo.admin.manage.domain.entity.Role;

public interface ShiroDbService {
	ShiroUser findById(String id);
	ShiroUser findByUsername(String username);
	List<Role> findAll();
	Collection<Role> findByShiroUser(ShiroUser shiroUser);
	
	Collection<String> makeRoles(Collection<Role> roles, ShiroUser shiroUser);
	Collection<String> makePermissions(Collection<Role> roles, ShiroUser shiroUser);
}
