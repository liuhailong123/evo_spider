package cn.com.evo.admin.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.exception.ExistedException;
import com.frameworks.core.exception.NotAllowDeleteException;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.web.constants.WebConsts;

import cn.com.evo.admin.manage.domain.entity.Module;
import cn.com.evo.admin.manage.domain.entity.Permission;
import cn.com.evo.admin.manage.repository.ModuleRepository;
import cn.com.evo.admin.manage.repository.PermissionRepository;
import cn.com.evo.admin.manage.service.ModuleService;

@Service
@Transactional
public class ModuleServiceImpl extends AbstractBaseService<Module, String> implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Module> findByParentId(String parentId) {
        return moduleRepository.findByStatusAndParentIdOrderByPriorityAsc(1, parentId);
    }

    @Override
    public void save(Module entity) {
        Module module = moduleRepository.findByCode(entity.getCode());
        if (null != module) {
            throw new ExistedException("编码已存在！");
        }
        List<Permission> permissions = new ArrayList<Permission>(0);
        for (Permission uiPerm : entity.getPermissions()) {
            if (StringUtils.isNoneBlank(uiPerm.getCode())) { // 已选中的
                uiPerm.setModule(entity);
                permissionRepository.save(uiPerm);
                permissions.add(uiPerm);
            }
        }
        entity.setPermissions(permissions);
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(Module entity) {
        Module module = super.findById(entity.getId());
        module.setName(entity.getName());
        module.setCode(entity.getCode());
        module.setIcon(entity.getIcon());
        module.setUrl(entity.getUrl());
        module.setStatus(entity.getStatus());
        module.setPriority(entity.getPriority());
        module.setDescription(entity.getDescription());
        module.setParent(entity.getParent());
        module.onUpdate();
        super.saveOrUpdate(module);
    }

    @Override
    public void changePermissions(Module entity) {
        Module module = super.findById(entity.getId());
        for (Permission uiPerm : entity.getPermissions()) {
            if (StringUtils.isNoneBlank(uiPerm.getCode())) { // 已选中的
                if (uiPerm.getId() == null) {// 新增
                    uiPerm.setModule(module);
                    permissionRepository.save(uiPerm);
                    module.getPermissions().add(uiPerm);
                }
            } else { // 未选中的
                if (uiPerm.getId() != null) {// 已存在 且删除
                    for (Permission dbPerm : module.getPermissions()) {
                        if (dbPerm.getId() == uiPerm.getId()) {
                            dbPerm.setModule(null);
                            module.getPermissions().remove(dbPerm);
                            permissionRepository.delete(dbPerm);
                            break;
                        }
                    }
                }
            }
        }
        super.saveOrUpdate(module);
    }

    @Override
    public void deleteById(String id) {
        Module entity = this.findById(id);
        if (entity.getLevel() == WebConsts.DEFAULT_ROOT_LEVEL) {
            throw new NotAllowDeleteException("系统工作台不允许删除!");
        }
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void deleteByEntity(Module entity) {
        if (entity.getLevel() == WebConsts.DEFAULT_ROOT_LEVEL) {
            throw new NotAllowDeleteException("系统工作台不允许删除!");
        }
        super.deleteByEntity(entity);
    }

    @Override
    public void deleteByEntities(Iterable<Module> entities) {
        for (Module entity : entities) {
            if (entity.getLevel() == WebConsts.DEFAULT_ROOT_LEVEL) {
                throw new NotAllowDeleteException("系统工作台不允许删除!");
            }
        }
        super.deleteByEntities(entities);
    }

    @Override
    protected BaseRepository<Module, String> getBaseRepository() {
        return moduleRepository;
    }

}
