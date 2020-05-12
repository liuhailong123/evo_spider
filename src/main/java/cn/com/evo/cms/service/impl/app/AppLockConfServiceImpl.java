package cn.com.evo.cms.service.impl.app;

import cn.com.evo.cms.domain.entity.app.AppLockConf;
import cn.com.evo.cms.domain.entity.vip.UserLockRecord;
import cn.com.evo.cms.repository.app.AppLockConfRepository;
import cn.com.evo.cms.service.app.AppLockConfService;
import cn.com.evo.cms.service.vip.UserLockRecordService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppLockConfServiceImpl extends AbstractBaseService<AppLockConf, String> implements AppLockConfService {

    @Autowired
    private AppLockConfRepository appLockConfRepository;

    @Autowired
    private UserLockRecordService userLockRecordService;

    @Override
    protected BaseRepository<AppLockConf, String> getBaseRepository() {
        return appLockConfRepository;
    }


    @Override
    public List<AppLockConf> findByAppId(String appId) {
        return appLockConfRepository.findByAppId(appId);
    }

    @Override
    public List<AppLockConf> findByContentId(String contentId) {
        return appLockConfRepository.findByContentId(contentId);
    }

    @Override
    public void deleteById(String id) {
        List<UserLockRecord> userLockRecords = userLockRecordService.findByAppLockConfId(id);
        if (userLockRecords != null) {
            if (userLockRecords.size() > 0) {
                throw new RuntimeException("该应用锁配置已被用户使用，要删除请联系-系统管理员");
            }
        }
        super.deleteById(id);
    }
}
