package cn.com.evo.cms.repository.cms;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.cms.SectionTemplate;

public interface SectionTemplateRepository extends BaseRepository<SectionTemplate, String> {
	SectionTemplate getByTemplateCode(String code);
}
