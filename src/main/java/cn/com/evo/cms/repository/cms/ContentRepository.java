package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.Content;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends BaseRepository<Content, String> {
    @Modifying
    @Query(value = "update cms_content set id=:contentId where id=:oldContentId", nativeQuery = true)
    void dataMoveUpdate(@Param("contentId") String contentId, @Param("oldContentId") String oldContentId);

    @Query(value = "select a.* from cms_content a,cms_catalogue_relation b where a.id=b.bId and b.publish=1 and  a.name = ?1 ORDER BY a.sort limit 1", nativeQuery = true)
    Content getByNamePublish(String name);

    Content getByName(String name);

    List<Content> findByPIdOrderBySortAsc(String id);

    List<Content> findByName(String name);

    @Query(value = "select count(*) from cms_content where pId=?1 and enable = 1", nativeQuery = true)
    Long findByPIdTotal(String pId);

    List<Content> findByCode(String code);

    List<Content> findByNameAndClassify(String name, Integer classify);

    List<Content> findByPIdAndClassifyAndEnableOrderBySortAsc(String contentId, Integer classify, Integer enable);

    /**
     * 根据剧集总集id和当前集数获取下一集的内容对象
     *
     * @param pId  剧集总集id
     * @param sort 当前集数
     * @return
     */
    @Query(value = "SELECT * FROM cms_content c WHERE c.pId=?1 AND c.sort = ?2 AND c.ENABLE=1 ORDER BY c.sort LIMIT 1", nativeQuery = true)
    Content getContentByIdAndSort(String pId, Integer sort);

    List<Content> findByPIdAndSynType(String pId, Integer synType);

    @Query(value = "select * from cms_content c, cms_source_rel r where r.sourceId=?1 and r.fId=c.id", nativeQuery = true)
    Content findByVideoId(String assetId);
}
