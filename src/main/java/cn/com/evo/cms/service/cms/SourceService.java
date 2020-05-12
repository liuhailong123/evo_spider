package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.Source;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface SourceService extends BaseService<Source, String> {

    /**
     * 资源id更新
     *
     * @param sourceId
     * @param oldSourceId
     */
    void dataMoveUpdate(String sourceId, String oldSourceId);

    /**
     * 根据资源名称模糊获取资源对象List
     *
     * @param name
     * @return
     */
    List<Source> findLikeName(String name);

    /**
     * 根据资源名称 获取资源对象List
     *
     * @param name
     * @return
     */
    List<Source> findByName(String name);

    Source getByName(String name);

    /**
     * 根据资源名称,资源类型 获取资源对象List
     *
     * @param name
     * @param type
     * @return
     */
    List<Source> findByNameAndType(String name, int type);
}
