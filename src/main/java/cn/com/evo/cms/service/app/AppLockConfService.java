package cn.com.evo.cms.service.app;

import cn.com.evo.cms.domain.entity.app.AppLockConf;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface AppLockConfService extends BaseService<AppLockConf, String> {


    /**
     * 根据应用Id获取 应用加锁配置
     * @param appId
     * @return
     */
    List<AppLockConf> findByAppId(String appId);

    /**
     * 根据栏目id获取 应用加锁配置
     * @param contentId
     * @return
     */
    List<AppLockConf> findByContentId(String contentId);
}
