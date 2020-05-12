package cn.com.evo.cms.repository.cms;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.cms.AuxiliaryFallTemplate;

public interface AuxiliaryFallTemplateRepository extends BaseRepository<AuxiliaryFallTemplate, String> {

	AuxiliaryFallTemplate getByColumnId(String columnId);
}
