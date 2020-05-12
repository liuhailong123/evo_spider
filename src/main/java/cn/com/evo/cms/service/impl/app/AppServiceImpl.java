package cn.com.evo.cms.service.impl.app;

import cn.com.evo.cms.domain.entity.app.App;
import cn.com.evo.cms.domain.entity.app.AppLockConf;
import cn.com.evo.cms.repository.app.AppRepository;
import cn.com.evo.cms.service.app.AppLockConfService;
import cn.com.evo.cms.service.app.AppService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppServiceImpl extends AbstractBaseService<App, String> implements AppService {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private AppLockConfService appLockConfService;

    @Override
    protected BaseRepository<App, String> getBaseRepository() {
        return appRepository;
    }

    @Override
    public void deleteById(String id) {
        List<AppLockConf> appLockConfs = appLockConfService.findByAppId(id);
        if (appLockConfs != null) {
            if (appLockConfs.size() > 0) {
                throw new RuntimeException("该应用下有应用锁配置，请先删除应用锁配置");
            }
        }
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public App getByPackageName(String packageName) {
        return appRepository.getByPackageName(packageName);
    }
}
