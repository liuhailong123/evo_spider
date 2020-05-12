package cn.com.evo.cms.repository.app;

import cn.com.evo.cms.domain.entity.app.App;
import com.frameworks.core.repository.BaseRepository;

public interface AppRepository extends BaseRepository<App, String> {
    App getByPackageName(String packageName);
}
