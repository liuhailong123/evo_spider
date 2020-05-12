package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.SectionTemplate;
import cn.com.evo.cms.repository.cms.SectionTemplateRepository;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SectionTemplateService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class SectionTemplateServiceImpl extends AbstractBaseService<SectionTemplate, String> implements SectionTemplateService {

    @Autowired
    private SectionTemplateRepository cmsSectionTemplateRepository;

    @Autowired
    private PictureService pictureService;

    @Override
    protected BaseRepository<SectionTemplate, String> getBaseRepository() {
        return cmsSectionTemplateRepository;
    }

    @Override
    public void update(SectionTemplate entity, MultipartFile file) {
        if ("".equals(entity.getContent()) && file != null) {
            MultipartFile[] files = {file};
//			JSONArray ja=pictureService.upLoad(files);
//			JSONObject data = ja.getJSONObject(0);
//			entity.setContent(data.getString("fileName"));
        }
        super.update(entity);
    }

    @Override
    public void deleteFile(String id) {
        SectionTemplate cmsSectionTemplate = this.findById(id);
        cmsSectionTemplate.setContent("");
        this.update(cmsSectionTemplate);
    }

    @Override
    public void save(SectionTemplate entity, MultipartFile file) {
        if (file != null) {
            MultipartFile[] files = {file};
//			JSONArray ja=pictureService.upLoad(files);
//			JSONObject data = ja.getJSONObject(0);
//			entity.setContent(data.getString("fileName"));
        }
        super.save(entity);
    }

    @Override
    public SectionTemplate getByTemplateCode(String code) {
        return cmsSectionTemplateRepository.getByTemplateCode(code);
    }

}
