package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.Active;
import cn.com.evo.cms.domain.entity.cms.ActiveApply;
import com.frameworks.core.repository.BaseRepository;

public interface ActiveApplyRepository extends BaseRepository<ActiveApply, String> {


    ActiveApply getByActiveIdAndUserId(String activeId, String userId);
}
