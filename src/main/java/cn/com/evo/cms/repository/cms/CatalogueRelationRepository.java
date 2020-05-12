package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.web.api.vo.CataloguArticleApiVo;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatalogueRelationRepository extends BaseRepository<CatalogueRelation, String> {

    CatalogueRelation getByAIdAndBIdAndType(String roleId, String siteId, int type);

    List<CatalogueRelation> findByBId(String bId);

    List<CatalogueRelation> findByBIdAndType(String bId, int type);

    List<CatalogueRelation> findByAIdAndType(String aId, int type);

    List<CatalogueRelation> findByAIdAndTypeOrderBySort(String aId, int type);

    List<CatalogueRelation> findByAIdAndTypeAndPublishOrderBySortAsc(String columnId, int type, int publish);

    List<CatalogueRelation> findByAIdAndContentTypeAndPublishOrderBySortAsc(String columnId, int contentType, int publish);

    /**
     * 根据栏目编码获取栏目下businessType=2的推荐内容（分页）
     *
     * @param columnCode
     * @param start
     * @param pageSize
     * @return
     */
    @Query(value = "SELECT " +
            "r.* " +
            "FROM " +
            "cms_catalogue_relation r, " +
            "cms_column c " +
            "WHERE " +
            "r.aId = c.id " +
            "AND c.columnCode LIKE ? " +
            "AND r.businessType = 2 " +
            "AND r.publish = 1 " +
            "ORDER BY r.sort limit ?,?", nativeQuery = true)
    List<CatalogueRelation> findLikeByColumnCode(String columnCode, Integer start, Integer pageSize);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "select c.id, c.title, c.cover, c.author, c.readNum, c.dateTime, r.price from spi_spider_content c, cms_catalogue_relation r " +
            "where c.id=r.bid and r.aId=:aId ", nativeQuery = true)
    List<Object[]> findByAId(@Param("aId")String id);

    CatalogueRelation getByAIdAndBId(String cId, String sId);
}
