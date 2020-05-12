package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.IndexConf;
import cn.com.evo.cms.domain.entity.cms.Section;
import com.frameworks.core.service.BaseService;


public interface IndexConfService extends BaseService<IndexConf, String> {


    IndexConf getByAppId(String appId);
}
