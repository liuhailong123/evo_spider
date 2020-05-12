package cn.com.evo.cms.service.cms;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.cms.FallTemplate;

public interface FallTemplateService extends BaseService<FallTemplate, String> {

	FallTemplate getByColumnId(String columnId);

}
