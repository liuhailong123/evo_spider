package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.Active;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ActiveService extends BaseService<Active, String> {

    /**
     * 保存活动 以及 与图片的关系
     *
     * @param entity
     * @param imgId
     */
    void save(Active entity, String imgId);

    /**
     * 更新活动 以及 与图片的关系
     *
     * @param entity
     * @param imgId
     */
    void update(Active entity, String imgId);

    /**
     * 根据活动id 和当前时间 获取在有效期内的活动
     *
     * @param id
     * @param thisTime
     * @return
     */
    Active getByIdAndThisTime(String id, String thisTime);
}
