package cn.com.evo.cms.repository.cms;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.cms.FallTemplatePosition;

public interface FallTemplatePositionRepository extends BaseRepository<FallTemplatePosition, String> {

	List<FallTemplatePosition> findByFallTemplateId(String templateId);

	List<FallTemplatePosition> findByFallTemplateIdOrderByPositionAsc(String fallTemplateId);

}
