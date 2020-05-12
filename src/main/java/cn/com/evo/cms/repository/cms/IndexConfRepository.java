package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.IndexConf;
import com.frameworks.core.repository.BaseRepository;

public interface IndexConfRepository extends BaseRepository<IndexConf, String> {

    IndexConf getByAppId(String appId);
}
