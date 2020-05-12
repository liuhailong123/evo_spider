package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.Module;

public interface ModuleService extends BaseService<Module, String> {

    List<Module> findByParentId(String parentId);

    void changePermissions(Module entity);

}
