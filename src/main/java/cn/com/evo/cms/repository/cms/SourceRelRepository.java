package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.SourceRel;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SourceRelRepository extends BaseRepository<SourceRel, String> {

    /**
     * 获取资源关系列表
     *
     * @param fId        外键id
     * @param sourceType 资源类型
     * @return
     */
    List<SourceRel> findByFIdAndSourcetype(String fId, int sourceType);

    /**
     * 获取资源关系对象
     *
     * @param fId          外键id
     * @param relType      关系类型
     * @param sourceId     资源id
     * @param sourceType   资源类型
     * @param businessType 业务类型
     * @return
     */
    SourceRel getByFIdAndRelTypeAndSourceIdAndSourcetypeAndBusinessType(String fId, int relType, String sourceId,
                                                                        int sourceType, int businessType);

    /**
     * 获取资源关系对象
     *
     * @param fId        外键id
     * @param relType    关系类型
     * @param sourceId   资源id
     * @param sourceType 资源类型
     * @return
     */
    SourceRel getByFIdAndRelTypeAndSourceIdAndSourcetype(String fId, int relType, String sourceId, int sourceType);

    /**
     * 获取资源关系对象
     *
     * @param buinessType 业务类型
     * @param columnId    外键id
     * @param relType     关系类型
     * @param sourceType  资源类型
     * @return
     */
    SourceRel getByBusinessTypeAndFIdAndRelTypeAndSourcetype(int buinessType, String columnId, int relType,
                                                             int sourceType);

    /**
     * 获取资源关系List
     *
     * @param fId
     * @param relType
     * @param sourceType
     * @param businessType
     * @return
     */
    @Query(value = "select * from cms_source_rel where fId=?1 and relType=?2 and sourcetype=?3 and businessType=?4", nativeQuery = true)
    List<SourceRel> findByFIdAndRelTypeAndSourcetypeAndBusinessType(String fId, int relType, int sourceType,
                                                                    int businessType);

    List<SourceRel> findByFIdAndRelType(String columnId, int relType);

    List<SourceRel> findByFId(String id);

    List<SourceRel> findByFIdAndSourcetypeAndBusinessType(String id, int index, int type);

    List<SourceRel> findBySourceId(String sourceId);

    SourceRel getByFIdAndSourceId(String fId, String sourceId);
}
