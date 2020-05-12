package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.admin.manage.domain.entity.RolePermission;
import cn.com.evo.admin.manage.repository.RolePermissionRepository;
import cn.com.evo.admin.manage.service.RolePermissionService;

@Service
@Transactional
public class RolePermissionServiceImpl extends AbstractBaseService<RolePermission, String>
        implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public void save(RolePermission entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(RolePermission entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public List<RolePermission> findByRoleId(String roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }

    @Override
    public List<RolePermission> findByRoleIdAndModuleId(String roleId, String moduleId) {
        return rolePermissionRepository.findByRoleIdAndModuleId(roleId, moduleId);
    }

    @Override
    protected BaseRepository<RolePermission, String> getBaseRepository() {
        return rolePermissionRepository;
    }

}
