package cn.com.evo.cms.service.impl.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.cms.domain.entity.cms.AuxiliaryFallTemplate;
import cn.com.evo.cms.repository.cms.AuxiliaryFallTemplateRepository;
import cn.com.evo.cms.service.cms.AuxiliaryFallTemplateService;

@Service
@Transactional
public class AuxiliaryFallTemplateServiceImpl extends AbstractBaseService<AuxiliaryFallTemplate, String>
		implements AuxiliaryFallTemplateService {

	@Autowired
	private AuxiliaryFallTemplateRepository auxiliaryFallTemplateRepository;

	@Override
	protected BaseRepository<AuxiliaryFallTemplate, String> getBaseRepository() {
		return auxiliaryFallTemplateRepository;
	}

	@Override
	public AuxiliaryFallTemplate getByColumnId(String columnId) {
		return auxiliaryFallTemplateRepository.getByColumnId(columnId);
	}

	@Override
	public void deleteByColumnId(String columnId) {
		AuxiliaryFallTemplate entity = this.getByColumnId(columnId);
		if (entity != null) {
			this.deleteByEntity(entity);
		}
	}
}
