package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.Content;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentVoRepository extends BaseRepository<Content, String> {
    @Query(value = "SELECT " +
            "a.id, " +
            "a.NAME, " +
            "a.classify, " +
            "p.fileName, " +
            "p.type, " +
            "a.score  " +
            "FROM " +
            "( " +
            "SELECT " +
            "DISTINCT " +
            "c.id, " +
            "c.NAME, " +
            "c.classify, " +
            "MATCH (c.NAME, c.title, c.info, c.nameSpellShort, c.nameSpellLong, c.titleSpellShort, c.titleSpellLong ) AGAINST ( ?1 IN boolean MODE ) AS score  " +
            "FROM " +
            "cms_catalogue_relation r, " +
            "cms_column co, " +
            "cms_content c " +
            "WHERE " +
            "r.aId = co.id  " +
            "and r.bId = c.id  " +
            "AND MATCH (c.NAME, c.title, c.info, c.nameSpellShort, c.nameSpellLong, c.titleSpellShort, c.titleSpellLong ) AGAINST ( ?1 IN boolean MODE ) " +
            "AND c.ENABLE = 1  " +
            "AND c.classify != 3  " +
            "AND co.columnCode LIKE ?4  " +
            "AND r.type = 2  " +
            "AND r.publish=1 " +
            "LIMIT ?2,?3  " +
            ") a " +
            "LEFT JOIN cms_source_rel r ON r.fId = a.id  " +
            "AND r.sourcetype = 2  " +
            "AND r.relType = 1  " +
            "AND r.businessType = 1 " +
            "LEFT JOIN cms_source_picture p ON p.id = r.sourceId  " +
            "ORDER BY " +
            "score desc", nativeQuery = true)
    List<Object[]> searchByKeyword(String keyword, Integer start, Integer pageSize, String columnCode);

    /**
     * 根据关键词搜索（模糊查询）
     *
     * @param keyword
     * @param start
     * @param pageSize
     * @param columnCode
     * @return
     */
    @Query(value = "SELECT a.relId AS id,a.NAME,a.classify,p.fileName,p.type FROM (SELECT c.id,r.id AS relId,c.NAME,c.classify FROM cms_catalogue_relation r,cms_column co,cms_content c WHERE r.aId=co.id AND r.bId=c.id AND c.nameSpellShort LIKE CONCAT('%',?1,'%') AND c.ENABLE=1 AND c.classify !=3 AND co.columnCode LIKE CONCAT('%',?4,'%') AND r.type=2 AND r.publish=1 GROUP BY c.id LIMIT ?2,?3) a LEFT JOIN cms_source_rel r ON r.fId=a.id AND r.sourcetype=2 AND r.relType=1 AND r.businessType=1 LEFT JOIN cms_source_picture p ON p.id=r.sourceId ORDER BY a.NAME", nativeQuery = true)
    List<Object[]> searchByKeywordByLike(String keyword, Integer start, Integer pageSize, String columnCode);

    /**
     * 根据关键词搜索（模糊查询） 数据总数
     *
     * @param keyword
     * @param columnCode
     * @return
     */
    @Query(value = "SELECT count(a.id) FROM (SELECT c.id,r.id AS relId,c.NAME,c.classify FROM cms_catalogue_relation r,cms_column co,cms_content c WHERE r.aId=co.id AND r.bId=c.id AND c.nameSpellShort LIKE CONCAT('%',?1,'%') AND c.ENABLE=1 AND c.classify !=3 AND co.columnCode LIKE CONCAT('%',?2,'%') AND r.type=2 AND r.publish=1 GROUP BY c.id) a", nativeQuery = true)
    Long searchByKeywordTotal(String keyword, String columnCode);


    /**
     * 获取最多播放次数内容
     *
     * @param appId
     * @param start
     * @param pageSize
     * @param type
     * @return
     */
    @Query(value = "SELECT a.relId as id,a.NAME,a.classify,p.fileName,p.type FROM (SELECT c.id,r.id AS relId,c.NAME,c.classify FROM cms_content c,cms_catalogue_relation r,(SELECT tc.bizValue,count(1) AS count FROM total_content tc WHERE tc.appId=?1 AND tc.type=?4 GROUP BY tc.bizValue ORDER BY count DESC LIMIT ?2,?3) tot WHERE tot.bizValue=r.id AND c.id=r.bId AND c.ENABLE=1) a LEFT JOIN cms_source_rel r ON r.fId=a.id AND r.sourcetype=2 AND r.relType=1 AND r.businessType=1 LEFT JOIN cms_source_picture p ON p.id=r.sourceId", nativeQuery = true)
    List<Object[]> findBylikeLook(String appId, Integer start, Integer pageSize, int type);


    /**
     * 获取最多播放次数内容 数据总数
     *
     * @param appId
     * @param type
     * @return
     */
    @Query(value = "SELECT count(a.relId) FROM (SELECT c.id,r.id AS relId,c.NAME,c.classify FROM cms_content c,cms_catalogue_relation r,(SELECT tc.bizValue,count(1) AS count FROM total_content tc WHERE tc.appId=?1 AND tc.type=?2 GROUP BY tc.bizValue ORDER BY count DESC) tot WHERE tot.bizValue=r.id AND c.id=r.bId AND c.ENABLE=1) a LEFT JOIN cms_source_rel r ON r.fId=a.id AND r.sourcetype=2 AND r.relType=1 AND r.businessType=1 LEFT JOIN cms_source_picture p ON p.id=r.sourceId", nativeQuery = true)
    Long findBylikeLookTotal(String appId, int type);
}
