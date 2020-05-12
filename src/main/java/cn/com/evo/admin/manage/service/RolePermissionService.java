package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.RolePermission;

public interface RolePermissionService extends BaseService<RolePermission, String> {
    List<RolePermission> findByRoleId(String roleId);

    List<RolePermission> findByRoleIdAndModuleId(String roleId, String moduleId);
}
