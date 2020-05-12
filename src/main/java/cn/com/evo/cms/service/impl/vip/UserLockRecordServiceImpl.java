package cn.com.evo.cms.service.impl.vip;

import cn.com.evo.cms.domain.entity.vip.UserLockRecord;
import cn.com.evo.cms.repository.vip.UserLockRecordRepository;
import cn.com.evo.cms.service.vip.UserLockRecordService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserLockRecordServiceImpl extends AbstractBaseService<UserLockRecord, String> implements UserLockRecordService {

	@Autowired
	private UserLockRecordRepository userLockRecordRepository;


	@Override
	protected BaseRepository<UserLockRecord, String> getBaseRepository() {
		return userLockRecordRepository;
	}


	@Override
	public List<UserLockRecord> findByUserId(String userId) {
		return userLockRecordRepository.findByUserId(userId);
	}

	@Override
	public UserLockRecord findByUserIdAndAppLockConfId(String userId, String appLockConfId) {
		return userLockRecordRepository.findByUserIdAndAppLockConfId(userId, appLockConfId);
	}

	@Override
	public List<UserLockRecord> findByAppLockConfId(String appLockConfId) {
		return userLockRecordRepository.findByAppLockConfId(appLockConfId);
	}
}
