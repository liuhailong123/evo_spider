package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.IndexConf;
import cn.com.evo.cms.domain.entity.cms.IndexConfChild;
import com.frameworks.core.service.BaseService;

import java.util.List;


public interface IndexConfChildService extends BaseService<IndexConfChild, String> {


    List<IndexConfChild> findByIndexConfId(String indexConfId);
}
