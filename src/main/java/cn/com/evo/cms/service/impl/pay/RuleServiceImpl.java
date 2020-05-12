package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.Rule;
import cn.com.evo.cms.domain.entity.pay.ServerRuleRel;
import cn.com.evo.cms.repository.pay.RuleRepository;
import cn.com.evo.cms.service.pay.RuleService;
import cn.com.evo.cms.service.pay.ServerRuleRelService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RuleServiceImpl extends AbstractBaseService<Rule, String> implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    protected BaseRepository<Rule, String> getBaseRepository() {
        return ruleRepository;
    }

    @Autowired
    private ServerRuleRelService serverRuleRelService;

    @Override
    public void save(Rule entity) {
        Boolean falg = timeVerify(entity.getTakeEffectTime(), entity.getLoseEfficacyTime());
        if (!falg) {
            throw new RuntimeException("失效时间小于生效时间，请重新录入");
        }
        super.save(entity);
    }

    private Boolean timeVerify(String takeEffectTime, String loseEfficacyTime) {
        Boolean falg = false;
        Long tTime = Long.valueOf(takeEffectTime.replaceAll("-", "").replace(" ", "").replaceAll(":", ""));
        Long lTime = Long.valueOf(loseEfficacyTime.replaceAll("-", "").replace(" ", "").replaceAll(":", ""));
        if (tTime < lTime) {
            falg = true;
        }
        return falg;
    }

    @Override
    public void update(Rule entity) {
        List<ServerRuleRel> serverRuleRels = serverRuleRelService.findByRuleId(entity.getId());
        if (serverRuleRels != null) {
            if (serverRuleRels.size() > 0) {
                entity.setServerRuleRels(serverRuleRels);
            }
        }

        Boolean falg = timeVerify(entity.getTakeEffectTime(), entity.getLoseEfficacyTime());
        if (!falg) {
            throw new RuntimeException("失效时间小于生效时间，请重新录入");
        }
        super.update(entity);
    }


    @Override
    public void deleteById(String id) {
        List<ServerRuleRel> serverRuleRels = serverRuleRelService.findByRuleId(id);
        if (serverRuleRels != null) {
            if (serverRuleRels.size() > 0) {
                throw new RuntimeException("该规则被关联，请先删除相关产品服务");
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
    public List<Rule> findByProductCodeAndServerCode(String productCode, String serverCode) {
        return ruleRepository.findByProductCodeAndServerCode(productCode, serverCode);
    }
}
