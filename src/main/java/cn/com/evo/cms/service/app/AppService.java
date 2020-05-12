package cn.com.evo.cms.service.app;

import cn.com.evo.cms.domain.entity.app.App;
import com.frameworks.core.service.BaseService;

public interface AppService extends BaseService<App, String> {
    App getByPackageName(String packageName);
}
