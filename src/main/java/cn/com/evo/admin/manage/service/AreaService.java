package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.Area;

public interface AreaService extends BaseService<Area, String> {

    List<Area> findByParentId(String parentId);

}
