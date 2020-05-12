package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.enums.BusinessTypeEnum;
import cn.com.evo.cms.domain.enums.SourceRelTypeEnum;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.repository.cms.SourceRelRepository;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceRelService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SourceRelServiceImpl extends AbstractBaseService<SourceRel, String> implements SourceRelService {

    @Autowired
    private SourceRelRepository sourceRefRepository;
    @Autowired
    private PictureService pictureService;

    @Override
    protected BaseRepository<SourceRel, String> getBaseRepository() {
        return sourceRefRepository;
    }

    @Override
    public List<SourceRel> findBySourceId(String sourceId) {
        return sourceRefRepository.findBySourceId(sourceId);
    }

    @Override
    public List<SourceRel> findByFId(String fId, int sourceType) {
        return sourceRefRepository.findByFIdAndSourcetype(fId, sourceType);
    }

    @Override
    public SourceRel getSourceRel(String fId, int refType, String sourceId, int sourceType, int businessType) {
        return sourceRefRepository.getByFIdAndRelTypeAndSourceIdAndSourcetypeAndBusinessType(fId, refType, sourceId,
                sourceType, businessType);
    }

    @Override
    public SourceRel getSourceRel(String fId, int refType, String sourceId, int sourceType) {
        return sourceRefRepository.getByFIdAndRelTypeAndSourceIdAndSourcetype(fId, refType, sourceId, sourceType);
    }

    @Override
    public void save(Object image, String columnId) {
        if (image != null) {
            JSONObject jo = JSONObject.parseObject(image.toString());
            JSONArray imageIds = jo.getJSONArray("imageId");
            JSONArray businessTypes = jo.getJSONArray("businessType");
            for (int i = 0; i < imageIds.size(); i++) {
                // 入库前去重
                SourceRel entity = this.getSourceRel(columnId, SourceRelTypeEnum.columnRel.getIndex(), imageIds.getString(i),
                        SourceTypeEnum.image.getIndex(), businessTypes.getIntValue(i));
                if (entity == null) {
                    entity = new SourceRel();
                    entity.setfId(columnId);
                    entity.setRelType(SourceRelTypeEnum.columnRel.getIndex());
                    entity.setSourceId(imageIds.getString(i));
                    entity.setSourcetype(SourceTypeEnum.image.getIndex());
                    entity.setBusinessType(businessTypes.getIntValue(i));
                    this.save(entity);
                }
            }
        }

    }

    @Override
    public Picture getByFIdAndBuinessTypeAndRelTypeAndSourceType(String columnId, int buinessType, int relType,
                                                                 int sourceType) {
        try {
            Picture picture = new Picture();
            SourceRel sourceRel = sourceRefRepository.getByBusinessTypeAndFIdAndRelTypeAndSourcetype(buinessType,
                    columnId, relType, sourceType);
            if (sourceRel != null) {
                picture = pictureService.findById(sourceRel.getSourceId());
            }
            return picture;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveSourceRel(Object main) {
        JSONObject jo = JSONObject.parseObject(main.toString());
        // 保存背景图
        if (jo.getString("bgPictureId") != null) {
            // 入库前去重
            SourceRel entityBg = this.getSourceRel(jo.getString("id"), SourceRelTypeEnum.columnRel.getIndex(),
                    jo.getString("bgPictureId"), SourceTypeEnum.image.getIndex(),
                    BusinessTypeEnum.background.getIndex());
            if (entityBg == null) {
                entityBg = new SourceRel();
                entityBg.setfId(jo.getString("id"));
                entityBg.setRelType(SourceRelTypeEnum.columnRel.getIndex());
                entityBg.setSourceId(jo.getString("bgPictureId"));
                entityBg.setSourcetype(SourceTypeEnum.image.getIndex());
                entityBg.setBusinessType(BusinessTypeEnum.background.getIndex());
                this.save(entityBg);
            }
        } else {// 判断目录是否已存在背景图，存在则删除
            SourceRel sourceRel = sourceRefRepository.getByBusinessTypeAndFIdAndRelTypeAndSourcetype(
                    BusinessTypeEnum.background.getIndex(), jo.getString("id"), SourceRelTypeEnum.columnRel.getIndex(),
                    SourceTypeEnum.image.getIndex());
            if (sourceRel != null) {
                this.deleteByEntity(sourceRel);
            }
        }

        // 保存海报／封面
        if (jo.getString("coverPictureId") != null) {
            // 判断目录是否已存在封面图，存在则删除
            SourceRel sourceRel = sourceRefRepository.getByBusinessTypeAndFIdAndRelTypeAndSourcetype(
                    BusinessTypeEnum.cover.getIndex(), jo.getString("id"), SourceRelTypeEnum.columnRel.getIndex(),
                    SourceTypeEnum.image.getIndex());
            if (sourceRel != null) {
                this.deleteByEntity(sourceRel);
            }

            // 入库前去重
            SourceRel entityCover = this.getSourceRel(jo.getString("id"), SourceRelTypeEnum.columnRel.getIndex(),
                    jo.getString("coverPictureId"), SourceTypeEnum.image.getIndex(), BusinessTypeEnum.cover.getIndex());
            if (entityCover == null) {
                entityCover = new SourceRel();
                entityCover.setfId(jo.getString("id"));
                entityCover.setRelType(SourceRelTypeEnum.columnRel.getIndex());
                entityCover.setSourceId(jo.getString("coverPictureId"));
                entityCover.setSourcetype(SourceTypeEnum.image.getIndex());
                entityCover.setBusinessType(BusinessTypeEnum.cover.getIndex());
                this.save(entityCover);
            }
        } else {
            // 判断目录是否已存在封面图，存在则删除
            SourceRel sourceRel = sourceRefRepository.getByBusinessTypeAndFIdAndRelTypeAndSourcetype(
                    BusinessTypeEnum.cover.getIndex(), jo.getString("id"), SourceRelTypeEnum.columnRel.getIndex(),
                    SourceTypeEnum.image.getIndex());
            if (sourceRel != null) {
                this.deleteByEntity(sourceRel);
            }
        }
    }

    @Override
    public List<SourceRel> findByFIdAndRelTypeAndSourcetypeAndBusinessType(String fId, int relType, int sourceType,
                                                                           int businessType) {
        return sourceRefRepository.findByFIdAndRelTypeAndSourcetypeAndBusinessType(fId, relType, sourceType,
                businessType);
    }

    @Override
    public void deleteByColumnId(String columnId) {
        List<SourceRel> sourceRels = this.findByFIdAndRelType(columnId, SourceRelTypeEnum.columnRel.getIndex());
        if (sourceRels.size() > 0) {
            this.deleteByEntities(sourceRels);
        }
    }

    @Override
    public void changeBusinessType(String sourceRelId, Integer businessType) {
        SourceRel rel = this.findById(sourceRelId);
        if (rel != null) {
            rel.setBusinessType(businessType);
            super.update(rel);
        }
    }

    @Override
    public SourceRel getByFIdAndSourceId(String fId, String sourceId) {
        return sourceRefRepository.getByFIdAndSourceId(fId, sourceId);
    }

    @Override
    public List<SourceRel> findByFIdAndRelType(String columnId, int relType) {
        return sourceRefRepository.findByFIdAndRelType(columnId, relType);
    }

    @Override
    public void save(String objStr, Integer type, String contentId) {
        JSONArray ja = JSONArray.parseArray(objStr);
        for (int i = 0; i < ja.size(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            SourceRel entity = new SourceRel();
            entity.setfId(contentId);
            entity.setRelType(1);
            entity.setSourcetype(type);
            entity.setSourceId(jo.getString("id"));
            entity.setCreateDate(new Date());
            entity.setBusinessType(1);
            this.save(entity);
        }
    }

    @Override
    public void deleteByFId(String id) {
        List<SourceRel> sourceRels = sourceRefRepository.findByFId(id);
        super.deleteByEntities(sourceRels);
    }

    @Override
    public List<SourceRel> findByFIdAndSourcetypeAndBusinessType(String id, int index, int type) {
        return sourceRefRepository.findByFIdAndSourcetypeAndBusinessType(id, index, type);
    }
}
