package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.LimitFree;
import com.frameworks.core.repository.BaseRepository;

public interface LimitFreeRepository extends BaseRepository<LimitFree, String> {
    /**
     * 根据业务获取限免对象
     *
     * @param bizId
     * @param appId
     * @return
     */
    LimitFree getByBizIdAndAppId(String bizId, String appId);
}
