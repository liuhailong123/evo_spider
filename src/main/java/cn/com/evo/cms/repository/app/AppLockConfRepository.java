package cn.com.evo.cms.repository.app;

import cn.com.evo.cms.domain.entity.app.AppLockConf;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;


public interface AppLockConfRepository extends BaseRepository<AppLockConf, String> {

    List<AppLockConf> findByAppId(String appId);

    List<AppLockConf> findByContentId(String contentId);
}
