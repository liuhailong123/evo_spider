package cn.com.evo.cms.service.impl.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.cms.domain.entity.cms.AuxiliaryFallTemplate;
import cn.com.evo.cms.domain.entity.cms.FallTemplate;
import cn.com.evo.cms.repository.cms.FallTemplateRepository;
import cn.com.evo.cms.service.cms.AuxiliaryFallTemplateService;
import cn.com.evo.cms.service.cms.FallTemplatePositionService;
import cn.com.evo.cms.service.cms.FallTemplateService;

@Service
@Transactional
public class FallTemplateServiceImpl extends AbstractBaseService<FallTemplate, String> implements FallTemplateService {

	@Autowired
	private FallTemplateRepository fallTemplateRepository;

	@Autowired
	private FallTemplatePositionService fallTemplatePositionService;

	@Autowired
	private AuxiliaryFallTemplateService auxiliaryFallTemplateService;

	@Override
	protected BaseRepository<FallTemplate, String> getBaseRepository() {
		return fallTemplateRepository;
	}

	@Override
	public void deleteById(String id) {
		fallTemplatePositionService.deleteByFallTemplateId(id);
		super.deleteById(id);
	}

	@Override
	public void deleteByIds(String[] ids) {
		for (String id : ids) {
			this.deleteById(id);
		}
	}

	@Override
	public FallTemplate getByColumnId(String columnId) {
		AuxiliaryFallTemplate auxiliaryFallTemplate = auxiliaryFallTemplateService.getByColumnId(columnId);
		FallTemplate entity = auxiliaryFallTemplate.getFallTemplate();
		return entity;
	}
}
