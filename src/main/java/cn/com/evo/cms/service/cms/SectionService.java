package cn.com.evo.cms.service.cms;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.cms.Section;


public interface SectionService extends BaseService<Section, String> {

	void update(Section entity, String contentImage);

}
