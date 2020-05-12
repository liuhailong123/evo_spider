package cn.com.evo.cms.service.vip;

import cn.com.evo.cms.domain.entity.vip.UserFamily;
import com.frameworks.core.service.BaseService;

public interface UserFamilyService extends BaseService<UserFamily, String> {
    UserFamily getByUserId(String userId);

    void saveOrUpdateEntity(UserFamily userFamily);
}
