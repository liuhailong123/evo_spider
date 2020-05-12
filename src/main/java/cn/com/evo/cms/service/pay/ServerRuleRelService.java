package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.ServerRuleRel;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ServerRuleRelService extends BaseService<ServerRuleRel, String> {


    List<ServerRuleRel> findByRuleId(String ruleId);

    List<ServerRuleRel> findByServerId(String serverId);
}
