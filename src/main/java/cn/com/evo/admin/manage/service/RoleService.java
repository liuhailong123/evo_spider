package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.domain.entity.RolePermission;

public interface RoleService extends BaseService<Role, String> {
    List<Role> findByOrganizationId(String organizationId);

    /**
     * 分配权限
     * 
     * @param roleId
     * @param moduleId
     * @param permIds
     */
    void assign(String roleId, String moduleId, String[] permIds);

    List<RolePermission> findRolePermission(String roleId, String moduleId);
}
