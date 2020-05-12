package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.vo.cms.ContentSearchVo;
import cn.com.evo.cms.domain.vo.cms.PublishContentVo;
import cn.com.evo.cms.web.api.vo.CataloguArticleApiVo;
import com.frameworks.core.service.BaseService;
import com.frameworks.core.web.page.Pager;

import java.util.List;

public interface CatalogueRelationService extends BaseService<CatalogueRelation, String> {

    /**
     * @param aId
     * @param type 关系类型
     * @return
     */
    List<CatalogueRelation> findByAIdAndType(String aId, int type);

    CatalogueRelation getByAIdAndBIdAndType(String roleId, String siteId, int type);

    void delete(String columnId, String contentId, int type);

    void delete(String columnId, String[] contentIds, int type);

    /**
     * 发布
     *
     * @param id
     */
    void release(String id);

    void release(String[] ids);

    /**
     * 取消发布
     *
     * @param id
     */
    void releaseNo(String id);

    void releaseNo(String[] ids);

    /**
     * 根据bId获取关系对象
     *
     * @param bId
     * @return
     */
    List<CatalogueRelation> findByBId(String bId);

    /**
     * 根据栏目id获取已发布栏目内容关系并排序
     *
     * @param columnId
     * @param type
     * @return
     */
    List<CatalogueRelation> findByAIdAndTypeAndIsPublishOrderBySortAsc(String columnId, int type);

    /**
     * 根据栏目id、内容类型获取已发布栏目内容关系并排序
     * @param columnId
     * @param contentType
     * @return
     */
    List<CatalogueRelation> findByAIdAndContentTypeAndPublishOrderBySortAsc(String columnId, int contentType);
    /**
     * 设置内容推荐或者取消内容推荐
     *
     * @param ids  内容ids
     * @param type 类型；1-设置；2-取消
     */
    void setRecommend(String[] ids, Integer type);

    /**
     * 根据栏目code模糊查询相关内容
     *
     * @param columnCode 栏目编码
     * @param pageSize   每页数据数
     * @param pageNum    页码
     * @return
     */
    List<CatalogueRelation> findLikeByColumnCode(String columnCode, Integer pageSize, Integer pageNum);

    /**
     * 根据aId(目录id)获取电影或者剧集内容
     *
     * @param webPage         解析对象中的分页对象
     * @param contentSearchVo 内容搜索对象
     * @return
     */
    List<PublishContentVo> findMovieAndEpisodeContent(Pager webPage, ContentSearchVo contentSearchVo);

    /**
     * 根据aId(目录id)获取直播内容
     *
     * @param webPage         解析对象中的分页对象
     * @param contentSearchVo 内容搜索对象
     * @return
     */
    List<PublishContentVo> findLiveContent(Pager webPage, ContentSearchVo contentSearchVo);

    /**
     * 根据aId(目录id)获取直播内容
     *
     * @param webPage         解析对象中的分页对象
     * @param contentSearchVo 内容搜索对象
     * @return
     */
    List<PublishContentVo> findActiveContent(Pager webPage, ContentSearchVo contentSearchVo);

    /**
     * 根据aId(目录id)获取图书内容
     *
     * @param webPage         解析对象中的分页对象
     * @param contentSearchVo 内容搜索对象
     * @return
     */
    List<PublishContentVo> findBookContent(Pager webPage, ContentSearchVo contentSearchVo);

    /**
     * 根据aId（目录id）获取栏目内容
     * @param webPage 解析对象中的分页对象
     * @param contentSearchVo 内容搜索对象
     * @return
     */
    List<PublishContentVo> findColumnContent(Pager webPage, ContentSearchVo contentSearchVo);



    /**
     * 设置排序
     *
     * @param id    对象id
     * @param type  类型：1-置顶；2-插入
     * @param index 目标索引，插入位置
     */
    void setSort(String id, Integer type, Integer index);

    /**
     * 根据内容分类标签 自动挂载栏目与内容关系
     * @param columnId
     */
    void autoRel(String columnId);

    /**
     * 根据aId(目录)查询文章内容
     * @param webPage
     * @param contentSearchVo
     * @return
     */
    List<PublishContentVo> findSpiderContent(Pager webPage, ContentSearchVo contentSearchVo);

    /**
     * 根据Aid查询文章列表
     * @param id
     * @return
     */
    List<CataloguArticleApiVo> findByAId(String id);

    CatalogueRelation getByAIdAndBId(String cId, String sId);
}
