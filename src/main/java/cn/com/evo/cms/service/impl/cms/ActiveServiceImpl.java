package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.constant.Constant;
import cn.com.evo.cms.domain.entity.cms.Active;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.IndexConfChild;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.repository.cms.ActiveRepository;
import cn.com.evo.cms.repository.cms.IndexConfChildRepository;
import cn.com.evo.cms.service.cms.ActiveService;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.SourceRelService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActiveServiceImpl extends AbstractBaseService<Active, String> implements ActiveService {

    @Autowired
    private ActiveRepository activeRepository;
    @Autowired
    private IndexConfChildRepository indexConfChildRepository;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;

    @Override
    protected BaseRepository<Active, String> getBaseRepository() {
        return activeRepository;
    }

    @Override
    public void save(Active entity) {
        entity.setId(null);
        String path = Constant.cms_spPath;
        String style = "<html>" + "<head>" + "<style type=\"text/css\">\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" + "@font-face {\n"
                + "    font-family: 'QDD';\n" + "    src: url('" + (path)
                + "static/assets/fonts/font.ttf') format('truetype');\n" + "    font-weight: 100;\n"
                + "    font-style: normal;\n" + "}\n" + "</style>\n" + " </head>";
        entity.setOtherInfo(style + "<body background=''>xxxx</body></html>");
        super.save(entity);
    }

    @Override
    public void save(Active entity, String imgId) {
        this.save(entity);
        SourceRel sourceRel = sourceRelService.getSourceRel(entity.getId(), 1, imgId, 2);
        if (sourceRel == null) {
            sourceRel = new SourceRel();
            sourceRel.setBusinessType(1);
            sourceRel.setfId(entity.getId());
            sourceRel.setRelType(1);
            sourceRel.setSourceId(imgId);
            sourceRel.setSourcetype(2);
            sourceRelService.save(sourceRel);
        }
    }

    @Override
    public void update(Active entity, String imgId) {
        this.update(entity);
        boolean falg = false;
        List<SourceRel> sourceRels = sourceRelService.findByFId(entity.getId(), 2);
        if (sourceRels != null) {
            if (sourceRels.size() > 0) {
                SourceRel rel = sourceRels.get(0);
                rel.setSourceId(imgId);
                sourceRelService.update(rel);
                falg = true;
            }
        }
        if (!falg) {
            SourceRel sourceRel = new SourceRel();
            sourceRel.setBusinessType(1);
            sourceRel.setfId(entity.getId());
            sourceRel.setRelType(1);
            sourceRel.setSourceId(imgId);
            sourceRel.setSourcetype(2);
            sourceRelService.save(sourceRel);
        }
    }

    @Override
    public void deleteById(String id) {
        // 判断内容是否挂载至栏目
        List<CatalogueRelation> list = catalogueRelationService.findByBId(id);
        if (list.size() > 0) {
            throw new RuntimeException("该挂载已被挂载至栏目，请先断开相关关系后，再进行删除！！！");
        }

        // 检测内容是否被挂载至首页
        List<IndexConfChild> indexConfChildren = indexConfChildRepository.findByContentId(id);
        if (indexConfChildren.size() > 0) {
            throw new RuntimeException(" 该内容已被挂载至首页！请先断开相关关系后，再进行删除！！！");
        }

        List<SourceRel> sourceRels = sourceRelService.findByFId(id, 2);
        if (sourceRels != null) {
            if (sourceRels.size() > 0) {
                sourceRelService.deleteByEntities(sourceRels);
            }
        }
        super.deleteById(id);
    }

    @Override
    public Active getByIdAndThisTime(String id, String thisTime) {
        return activeRepository.getByIdAndThisTime(id, thisTime);
    }
}
