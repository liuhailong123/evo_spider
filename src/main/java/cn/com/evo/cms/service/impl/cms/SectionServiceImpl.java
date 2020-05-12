package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Section;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.enums.SourceRelTypeEnum;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.repository.cms.SectionRepository;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.SectionService;
import cn.com.evo.cms.service.cms.SourceRelService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SectionServiceImpl extends AbstractBaseService<Section, String> implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;

    @Override
    protected BaseRepository<Section, String> getBaseRepository() {
        return sectionRepository;
    }

    @Override
    public void deleteById(String id) {
        // 判断内容是否挂载至栏目
        List<CatalogueRelation> list = catalogueRelationService.findByBId(id);
        if (list.size() > 0) {
            throw new RuntimeException("该专题已被挂载至栏目，请先断开相关关系后，再进行删除！！！");
        }

        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void update(Section entity, String contentImage) {
        // picture
        if (contentImage != null && !"".equals(contentImage)) {
            JSONArray ja = JSONArray.parseArray(contentImage);
            for (int i = 0; i < ja.size(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                SourceRel sourceRel = sourceRelService.getSourceRel(entity.getId(), SourceRelTypeEnum.sectionRel.getIndex(),
                        jo.getString("imageId"), SourceTypeEnum.image.getIndex(), jo.getInteger("businessType"));
                if (sourceRel == null) {
                    sourceRel = new SourceRel();
                    sourceRel.setfId(entity.getId());
                    sourceRel.setRelType(SourceRelTypeEnum.sectionRel.getIndex());
                    sourceRel.setSourceId(jo.getString("imageId"));
                    sourceRel.setSourcetype(SourceTypeEnum.image.getIndex());
                    sourceRel.setBusinessType(jo.getInteger("businessType"));
                    sourceRelService.save(sourceRel);
                }
            }
        }
        super.update(entity);
    }

}
