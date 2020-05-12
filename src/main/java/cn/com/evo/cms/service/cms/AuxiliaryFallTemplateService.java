package cn.com.evo.cms.service.cms;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.cms.AuxiliaryFallTemplate;

public interface AuxiliaryFallTemplateService extends BaseService<AuxiliaryFallTemplate, String> {

	AuxiliaryFallTemplate getByColumnId(String columnId);

    void deleteByColumnId(String columnId);
}
