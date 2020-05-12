package cn.com.evo.admin.manage.service;

import cn.com.evo.admin.manage.domain.entity.ProvinceConstant;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ProvinceConstantService extends BaseService<ProvinceConstant, String> {

    List<ProvinceConstant> findConstants();
}
