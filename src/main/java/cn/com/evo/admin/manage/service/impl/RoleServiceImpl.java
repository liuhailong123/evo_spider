package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.exception.ExistedException;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Permission;
import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.domain.entity.RolePermission;
import cn.com.evo.admin.manage.repository.PermissionRepository;
import cn.com.evo.admin.manage.repository.RolePermissionRepository;
import cn.com.evo.admin.manage.repository.RoleRepository;
import cn.com.evo.admin.manage.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends AbstractBaseService<Role, String> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public void save(Role entity) {
        Role role = roleRepository.findByCode(entity.getCode());
        if (null != role) {
            throw new ExistedException("角色编码已存在!");
        }
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(Role entity) {
        Role role = this.findById(entity.getId());
        entity.setAccountRoles(role.getAccountRoles());
        entity.setOrganizationRoles(role.getOrganizationRoles());
        entity.setRolePermissions(role.getRolePermissions());
        entity.setCreateDate(role.getCreateDate());
        super.saveOrUpdate(entity);
    }

    @Override
    public List<Role> findByOrganizationId(String organizationId) {
        return this.roleRepository.findByOrganizationId(organizationId);
    }

    @Override
    public void assign(String roleId, String moduleId, String[] permIds) {
        // 获取已分配的权限
        List<RolePermission> assigneds = rolePermissionRepository.findByRoleIdAndModuleId(roleId, moduleId);
        // 查询将分配的权限
        List<Permission> assignings = Lists.newArrayList();
        if (null != permIds) { // 如果不为空，表示变更权限，为空则清空所有权限
            assignings = permissionRepository.findByIdIn(permIds);
        }
        // 找出撤销的权限
        List<RolePermission> revokes = Lists.newArrayList();
        for (RolePermission assigend : assigneds) {
            boolean isRevoke = true;
            for (Permission assigning : assignings) {
                if (assigend.getId() == assigning.getId()) {
                    isRevoke = false;
                }
            }
            if (isRevoke) {
                revokes.add(assigend);
            }
        }
        if (!revokes.isEmpty()) {
            rolePermissionRepository.delete(revokes);
        }
        // 找出新分配的
        List<RolePermission> assigns = Lists.newArrayList();
        Role role = this.findById(roleId);
        for (Permission assigning : assignings) {
            boolean isAssigning = true;
            for (RolePermission assigned : assigneds) {
                if (assigned.getId() == assigning.getId()) {
                    isAssigning = false;
                }
            }
            if (isAssigning) {
                RolePermission rp = new RolePermission();
                rp.setRole(role);
                rp.setPermission(assigning);
                assigns.add(rp);
            }
        }
        if (!assigns.isEmpty()) {
            rolePermissionRepository.save(assigns);
        }
    }

    @Override
    public List<RolePermission> findRolePermission(String roleId, String moduleId) {
        return rolePermissionRepository.findByRoleIdAndModuleId(roleId, moduleId);
    }

    @Override
    protected BaseRepository<Role, String> getBaseRepository() {
        return roleRepository;
    }

}
