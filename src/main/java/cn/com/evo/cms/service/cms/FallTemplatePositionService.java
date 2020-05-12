package cn.com.evo.cms.service.cms;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.cms.FallTemplatePosition;

public interface FallTemplatePositionService extends BaseService<FallTemplatePosition, String> {

	void deleteByFallTemplateId(String templateId);

	List<FallTemplatePosition> findByColumnId(String columnId);

	List<FallTemplatePosition> findByFallTemplateId(String fallTemplateId);
	
}
