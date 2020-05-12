package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.Permission;

public interface PermissionService extends BaseService<Permission, String> {

    List<Permission> findByModuleId(String moduleId);

}
