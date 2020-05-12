package cn.com.evo.cms.repository.vip;

import cn.com.evo.cms.domain.entity.vip.UserLockRecord;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;


public interface UserLockRecordRepository extends BaseRepository<UserLockRecord, String> {

    List<UserLockRecord> findByUserId(String userId);

    UserLockRecord findByUserIdAndAppLockConfId(String userId, String appLockConfId);

    List<UserLockRecord> findByAppLockConfId(String appLockConfId);
}
