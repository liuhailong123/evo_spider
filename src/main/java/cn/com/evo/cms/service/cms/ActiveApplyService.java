package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.Active;
import cn.com.evo.cms.domain.entity.cms.ActiveApply;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ActiveApplyService extends BaseService<ActiveApply, String> {

    /**
     * 根据活动ID 用户ID 获取用户活动报名记录
     *
     * @param activeId
     * @param userId
     * @return
     */
    ActiveApply getByActiveIdAndUserId(String activeId, String userId);
}
