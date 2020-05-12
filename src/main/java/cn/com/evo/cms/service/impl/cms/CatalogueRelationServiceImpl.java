package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.vo.cms.ContentSearchVo;
import cn.com.evo.cms.domain.vo.cms.PublishContentVo;
import cn.com.evo.cms.repository.cms.CatalogueRelationRepository;
import cn.com.evo.cms.repository.cms.CatalogueRelationRepositoryImpl;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.utils.EntityUtils;
import cn.com.evo.cms.web.api.vo.CataloguArticleApiVo;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.web.page.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatalogueRelationServiceImpl extends AbstractBaseService<CatalogueRelation, String>
        implements CatalogueRelationService {

    @Autowired
    private CatalogueRelationRepository catalogueRelationRepository;
    @Autowired
    private CatalogueRelationRepositoryImpl catalogueRelationRepositoryImpl;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private ContentService contentService;

    @Override
    protected BaseRepository<CatalogueRelation, String> getBaseRepository() {
        return catalogueRelationRepository;
    }

    @Override
    public List<CatalogueRelation> findByAIdAndType(String aId, int type) {

        return catalogueRelationRepository.findByAIdAndType(aId, type);
    }

    @Override
    public CatalogueRelation getByAIdAndBIdAndType(String roleId, String siteId, int type) {
        return catalogueRelationRepository.getByAIdAndBIdAndType(roleId, siteId, type);
    }

    @Override
    public void delete(String columnId, String contentId, int type) {
        CatalogueRelation entity = this.getByAIdAndBIdAndType(columnId, contentId, type);
        this.deleteByEntity(entity);
    }

    @Override
    public void delete(String columnId, String[] contentIds, int type) {
        for (String contentId : contentIds) {
            this.delete(columnId, contentId, type);
        }
    }

    @Override
    public void release(String id) {
        CatalogueRelation entity = this.findById(id);
        entity.setPublish(1);
        this.update(entity);
    }

    @Override
    public void release(String[] ids) {
        for (String id : ids) {
            this.release(id);
        }
    }

    @Override
    public void releaseNo(String id) {
        CatalogueRelation entity = this.findById(id);
        entity.setPublish(0);
        this.update(entity);
    }

    @Override
    public void releaseNo(String[] ids) {
        for (String id : ids) {
            this.releaseNo(id);
        }
    }

    @Override
    public List<CatalogueRelation> findByBId(String bId) {
        return catalogueRelationRepository.findByBId(bId);
    }

    @Override
    public List<CatalogueRelation> findByAIdAndTypeAndIsPublishOrderBySortAsc(String columnId, int type) {
        return catalogueRelationRepository.findByAIdAndTypeAndPublishOrderBySortAsc(columnId, type, 1);
    }

    @Override
    public List<CatalogueRelation> findByAIdAndContentTypeAndPublishOrderBySortAsc(String columnId, int contentType) {
        return catalogueRelationRepository.findByAIdAndContentTypeAndPublishOrderBySortAsc(columnId, contentType, 1);
    }

    @Override
    public void setRecommend(String[] ids, Integer type) {
        for (String id : ids) {
            CatalogueRelation entity = this.findById(id);
            if (type == 1) {
                entity.setBusinessType(2);
            } else {
                entity.setBusinessType(1);
            }
            super.update(entity);
        }
    }

    @Override
    public List<CatalogueRelation> findLikeByColumnCode(String columnCode, Integer pageSize, Integer pageNum) {
        Integer start = (pageNum - 1) * pageSize;
        List<CatalogueRelation> list = catalogueRelationRepository.findLikeByColumnCode(columnCode + "%",
                start, pageSize);
        return list;
    }

    @Override
    public List<PublishContentVo> findMovieAndEpisodeContent(Pager webPage, ContentSearchVo contentSearchVo) {
        // 获取相关数据
        List<PublishContentVo> publishContentVos = catalogueRelationRepositoryImpl.
                findMovieAndEpisodeContent(webPage, contentSearchVo);
        return publishContentVos;
    }

    @Override
    public List<PublishContentVo> findLiveContent(Pager webPage, ContentSearchVo contentSearchVo) {
        // 获取相关数据
        List<PublishContentVo> publishContentVos = catalogueRelationRepositoryImpl.
                findLiveContent(webPage, contentSearchVo);
        return publishContentVos;
    }

    @Override
    public List<PublishContentVo> findActiveContent(Pager webPage, ContentSearchVo contentSearchVo) {
        // 获取相关数据
        List<PublishContentVo> publishContentVos = catalogueRelationRepositoryImpl.
                findActiveContent(webPage, contentSearchVo);
        return publishContentVos;
    }

    @Override
    public List<PublishContentVo> findBookContent(Pager webPage, ContentSearchVo contentSearchVo) {
        // 获取相关数据
        List<PublishContentVo> publishContentVos = catalogueRelationRepositoryImpl.
                findBookContent(webPage, contentSearchVo);
        return publishContentVos;
    }

    @Override
    public List<PublishContentVo> findColumnContent(Pager webPage, ContentSearchVo contentSearchVo) {
        // 获取相关数据
        List<PublishContentVo> publishContentVos = catalogueRelationRepositoryImpl.
                findColumnContent(webPage, contentSearchVo);
        return publishContentVos;
    }

    @Override
    public List<PublishContentVo> findSpiderContent(Pager webPage, ContentSearchVo contentSearchVo) {
        // 获取相关数据
        List<PublishContentVo> publishContentVos = catalogueRelationRepositoryImpl.
                findSpiderContent(webPage, contentSearchVo);
        return publishContentVos;
    }

    @Override
    public List<CataloguArticleApiVo> findByAId(String id) {
        List<Object[]> objects = catalogueRelationRepository.findByAId(id);
        CataloguArticleApiVo cataloguArticleApiVo = new CataloguArticleApiVo();
        List<CataloguArticleApiVo> cataloguArticleApiVos = EntityUtils.castEntity(objects, CataloguArticleApiVo.class, cataloguArticleApiVo);
        return cataloguArticleApiVos;
    }

    @Override
    public CatalogueRelation getByAIdAndBId(String cId, String sId) {
        return catalogueRelationRepository.getByAIdAndBId(cId, sId);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void save(CatalogueRelation entity) {
        //保存前 去重
        CatalogueRelation catalogueRelation = this.getByAIdAndBIdAndType(entity.getAId(), entity.getBId(), entity.getType());
        if (catalogueRelation == null) {
            super.save(entity);
        }
    }

    @Override
    public void setSort(String id, Integer type, Integer index) {
        if (type == 1) {
            //如果类型是置顶，则意味着目标索引为 1
            index = 1;
        } else {
            //如果类型是插入，则意味着目标索引为设置值
        }
        //原对象
        CatalogueRelation srcObj = this.findById(id);

        // 获取该目录下的全部内容
        List<CatalogueRelation> list = catalogueRelationRepository.findByAIdAndTypeOrderBySort(srcObj.getAId(), 2);
        int i = index;
        for (CatalogueRelation catalogueRelation : list) {
            if (!catalogueRelation.getId().equals(id)) {
                if (catalogueRelation.getSort() >= index) {
                    i++;
                    // 所有当前排序索引，大于目标排序索引的数据，将重新排序
                    catalogueRelation.setSort(i);
                }
            } else {
                catalogueRelation.setSort(index);
            }
        }

        this.save(list);
    }

    @Override
    public void autoRel(String columnId) {
        Column column = columnService.findById(columnId);
        String columnName = column.getName();


        List<Content> list = contentService.findAll();
        for (Content content : list) {
            if(content.getEnable() == 1){
                // 启用的内容
                if(content.getClassify() == 1 || content.getClassify() == 2){
                    // 电影或者剧集
                    if(StringUtils.isNotBlank(content.getClassifyTags())){
                        if(content.getClassifyTags().contains(columnName)){
                            // 栏目名称==内容分类标签
                            CatalogueRelation entity = new CatalogueRelation();
                            entity.setAId(columnId);
                            entity.setBId(content.getId());
                            entity.setType(2);
                            entity.setBusinessType(1);
                            entity.setPublish(0);
                            entity.setIsHot(0);
                            entity.setSort(1);
                            entity.setContentType(content.getClassify());
                            this.save(entity);
                        }
                    }
                }
            }
        }
    }


}
