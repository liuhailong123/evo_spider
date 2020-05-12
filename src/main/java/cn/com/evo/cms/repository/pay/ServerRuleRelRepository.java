package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.ServerRuleRel;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface ServerRuleRelRepository extends BaseRepository<ServerRuleRel, String> {

    ServerRuleRel getByServerIdAndRuleId(String serverId, String ruleId);

    List<ServerRuleRel> findByRuleId(String ruleId);

    List<ServerRuleRel> findByServerId(String serverId);
}
