package cn.com.evo.cms.repository.vip;

import cn.com.evo.cms.domain.entity.vip.UserFamily;
import com.frameworks.core.repository.BaseRepository;

public interface UserFamilyRepository extends BaseRepository<UserFamily, String> {
    UserFamily getByUserId(String userId);
}
