package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface SourceRelService extends BaseService<SourceRel, String> {
    /**
     * 获取资源关系列表
     *
     * @param sourceId 资源id
     * @return
     */
    List<SourceRel> findBySourceId(String sourceId);

    /**
     * 获取内容资源列表
     *
     * @param fId        外键id
     * @param sourceType 资源类型；
     * @return
     */
    List<SourceRel> findByFId(String fId, int sourceType);

    /**
     * 获取内容资源关系对象
     *
     * @param fId          外键id
     * @param refType      关系类型
     * @param sourceId     资源id
     * @param sourceType   资源类型
     * @param businessType 业务类型
     * @return
     */
    SourceRel getSourceRel(String fId, int refType, String sourceId, int sourceType, int businessType);

    /**
     * 获取内容资源关系对象
     *
     * @param fId        外键id
     * @param refType    关系类型
     * @param sourceId   资源id
     * @param sourceType 资源类型
     * @return
     */
    SourceRel getSourceRel(String fId, int refType, String sourceId, int sourceType);


    /**
     * 获取图片对象
     *
     * @param fId
     * @param buinessType 业务类型
     * @param RelType     关系类型
     * @param SourceType  资源类型
     * @return
     */
    Picture getByFIdAndBuinessTypeAndRelTypeAndSourceType(String fId, int buinessType, int RelType, int SourceType);


    /**
     * 获取资源关系List
     *
     * @param fId
     * @param relType      关系类型
     * @param sourceType   资源类型
     * @param businessType 业务类型
     * @return
     */
    List<SourceRel> findByFIdAndRelTypeAndSourcetypeAndBusinessType(String fId, int relType, int sourceType,
                                                                    int businessType);

    /**
     * 获取资源关系List
     *
     * @param fId
     * @param sourcetype   资源类型
     * @param businessType 业务类型
     * @return
     */
    List<SourceRel> findByFIdAndSourcetypeAndBusinessType(String fId, int sourcetype, int businessType);


    /**
     * 根据Fid 关系类型 获取 资源关联关系 List
     *
     * @param fId
     * @param relType 关系类型
     * @return
     */
    List<SourceRel> findByFIdAndRelType(String fId, int relType);


    /**
     * 记录 fId 与 资源List的关联关系
     *
     * @param objStr 资源List jsonStr
     * @param type   资源类型
     * @param fId
     */
    void save(String objStr, Integer type, String fId);


    /**
     * 保存栏目图片关系
     *
     * @param image
     * @param columnId
     */
    void save(Object image, String columnId);

    /**
     * 保存栏目图片关系
     *
     * @param main
     */
    void saveSourceRel(Object main);

    /**
     * 根据fId 删除 fId与资源的关联关系
     *
     * @param fId
     */
    void deleteByFId(String fId);

    /**
     * 根据栏目id 删除 栏目与资源的关联关系
     *
     * @param columnId
     */
    void deleteByColumnId(String columnId);

    /**
     * 更新资源关系对象的businessType属性
     *
     * @param sourceRelId
     * @param businessType
     */
    void changeBusinessType(String sourceRelId, Integer businessType);

    /**
     * 获取关系对象
     *
     * @param fId
     * @param sourceId
     * @return
     */
    SourceRel getByFIdAndSourceId(String fId, String sourceId);
}
