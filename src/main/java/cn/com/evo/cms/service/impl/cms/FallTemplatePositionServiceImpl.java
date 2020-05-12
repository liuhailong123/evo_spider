package cn.com.evo.cms.service.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.google.common.collect.Lists;

import cn.com.evo.cms.domain.entity.cms.FallTemplate;
import cn.com.evo.cms.domain.entity.cms.FallTemplatePosition;
import cn.com.evo.cms.repository.cms.FallTemplatePositionRepository;
import cn.com.evo.cms.service.cms.FallTemplatePositionService;
import cn.com.evo.cms.service.cms.FallTemplateService;

@Service
@Transactional
public class FallTemplatePositionServiceImpl extends AbstractBaseService<FallTemplatePosition, String>
		implements FallTemplatePositionService {

	@Autowired
	private FallTemplatePositionRepository fallTemplatePositionRepository;

	@Autowired
	private FallTemplateService fallTemplateService;

	@Override
	protected BaseRepository<FallTemplatePosition, String> getBaseRepository() {
		return fallTemplatePositionRepository;
	}

	@Override
	public void deleteByFallTemplateId(String templateId) {
		List<FallTemplatePosition> entitys = fallTemplatePositionRepository.findByFallTemplateId(templateId);
		for (FallTemplatePosition entity : entitys) {
			this.deleteByEntity(entity);
		}
	}

	@Override
	public void save(FallTemplatePosition entity) {
		FallTemplate fallTemplate = fallTemplateService.findById(entity.getFallTemplate().getId());
		List<FallTemplatePosition> entitys = fallTemplatePositionRepository
				.findByFallTemplateId(entity.getFallTemplate().getId());

		for (FallTemplatePosition fallTemplatePosition : entitys) {
			if (entity.getPosition() == fallTemplatePosition.getPosition()) {
				throw new RuntimeException("模版位重复");
			}
		}

		if (entity.getPosition() > fallTemplate.getCount()) {
			throw new RuntimeException("模版位超过该模版设定最大值");
		}

		if (entitys.size() >= fallTemplate.getCount()) {
			throw new RuntimeException("该模版下模版位已加满");
		}

		super.save(entity);
	}

	@Override
	public void update(FallTemplatePosition entity) {
		FallTemplate fallTemplate = fallTemplateService.findById(entity.getFallTemplate().getId());
		if (entity.getPosition() > fallTemplate.getCount()) {
			throw new RuntimeException("模版位超过该模版设定最大值");
		}

		List<FallTemplatePosition> entitys = fallTemplatePositionRepository
				.findByFallTemplateId(entity.getFallTemplate().getId());

		for (FallTemplatePosition fallTemplatePosition : entitys) {
			if (entity.getPosition() == fallTemplatePosition.getPosition()) {
				if (entity.getId() != fallTemplatePosition.getId()
						&& !entity.getId().equals(fallTemplatePosition.getId())) {
					throw new RuntimeException("模版位重复");
				}
			}
		}
		super.update(entity);
	}

	@Override
	public List<FallTemplatePosition> findByColumnId(String columnId) {
		List<FallTemplatePosition> entitys = Lists.newArrayList();
		FallTemplate fallTemplate = fallTemplateService.getByColumnId(columnId);
		if (fallTemplate != null) {
			entitys = this.findByFallTemplateId(fallTemplate.getId());
		}
		return entitys;
	}

	@Override
	public List<FallTemplatePosition> findByFallTemplateId(String fallTemplateId) {
		return fallTemplatePositionRepository.findByFallTemplateIdOrderByPositionAsc(fallTemplateId);
	}

}
