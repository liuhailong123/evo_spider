package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.Server;
import cn.com.evo.cms.domain.entity.pay.ServerRuleRel;
import cn.com.evo.cms.repository.pay.ServerRepository;
import cn.com.evo.cms.service.pay.ServerRuleRelService;
import cn.com.evo.cms.service.pay.ServerService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServerServiceImpl extends AbstractBaseService<Server, String> implements ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ServerRuleRelService serverRuleRelService;

    @Override
    protected BaseRepository<Server, String> getBaseRepository() {
        return serverRepository;
    }

    @Override
    public void update(Server entity) {
        List<ServerRuleRel> serverRuleRels = serverRuleRelService.findByServerId(entity.getId());
        if (serverRuleRels != null) {
            if (serverRuleRels.size() > 0) {
                entity.setServerRuleRels(serverRuleRels);
            }
        }
        super.update(entity);
    }

    @Override
    public void save(Server entity) {
        Server server = serverRepository.getByCode(entity.getCode());
        if (server != null) {
            throw new RuntimeException("服务编码重复，请重新录入");
        }
        super.save(entity);
    }

    @Override
    public void deleteById(String id) {
        List<ServerRuleRel> serverRuleRels = serverRuleRelService.findByServerId(id);
        if (serverRuleRels != null) {
            if (serverRuleRels.size() > 0) {
                throw new RuntimeException("该服务被关联，请先删除相关产品服务");
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
    public Server getByCode(String code) {
        return serverRepository.getByCode(code);
    }
}
