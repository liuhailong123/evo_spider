package cn.com.evo.cms.service.cms;

import org.springframework.web.multipart.MultipartFile;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.cms.SectionTemplate;

public interface SectionTemplateService extends BaseService<SectionTemplate, String> {

	void update(SectionTemplate entity,MultipartFile file);

	void deleteFile(String id);
	
    void save(SectionTemplate entity,MultipartFile file);
	
    SectionTemplate getByTemplateCode(String code);
}
