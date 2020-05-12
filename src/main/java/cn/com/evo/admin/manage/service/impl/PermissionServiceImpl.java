package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.admin.manage.domain.entity.Permission;
import cn.com.evo.admin.manage.repository.PermissionRepository;
import cn.com.evo.admin.manage.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl extends AbstractBaseService<Permission, String> implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void save(Permission entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(Permission entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public List<Permission> findByModuleId(String moduleId) {
        return permissionRepository.findByModuleId(moduleId);
    }

    @Override
    protected BaseRepository<Permission, String> getBaseRepository() {
        return permissionRepository;
    }

}
