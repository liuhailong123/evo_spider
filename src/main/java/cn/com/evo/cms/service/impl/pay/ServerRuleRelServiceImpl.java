package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.ProductServerRel;
import cn.com.evo.cms.domain.entity.pay.ServerRuleRel;
import cn.com.evo.cms.repository.pay.ServerRuleRelRepository;
import cn.com.evo.cms.service.pay.ProductServerRelService;
import cn.com.evo.cms.service.pay.ServerRuleRelService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServerRuleRelServiceImpl extends AbstractBaseService<ServerRuleRel, String> implements ServerRuleRelService {

    @Autowired
    private ServerRuleRelRepository serverRuleRelRepository;

    @Autowired
    private ProductServerRelService productServerRelService;

    @Override
    protected BaseRepository<ServerRuleRel, String> getBaseRepository() {
        return serverRuleRelRepository;
    }

    @Override
    public void save(ServerRuleRel entity) {
        ServerRuleRel serverRuleRel = serverRuleRelRepository.getByServerIdAndRuleId(entity.getServer().getId(), entity.getRule().getId());
        if (serverRuleRel == null) {
            super.save(entity);
        } else {
            throw new RuntimeException("已存在该产品服务:服务:" + entity.getServer().getName() + "|规则：" + entity.getRule().getName());
        }
    }

    @Override
    public List<ServerRuleRel> findByRuleId(String ruleId) {
        return serverRuleRelRepository.findByRuleId(ruleId);
    }

    @Override
    public List<ServerRuleRel> findByServerId(String serverId) {
        return serverRuleRelRepository.findByServerId(serverId);
    }

    @Override
    public void deleteById(String id) {
        List<ProductServerRel> productServerRels = productServerRelService.findByServerRuleRelId(id);
        if (productServerRels != null) {
            if (productServerRels.size() > 0) {
                throw new RuntimeException("该产品服务已被关联，请先删除与其的相关产品套餐关联");
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

}
