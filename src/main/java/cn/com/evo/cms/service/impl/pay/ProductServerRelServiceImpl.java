package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.*;
import cn.com.evo.cms.repository.pay.ProductServerRelRepository;
import cn.com.evo.cms.service.pay.ProductServerRelService;
import cn.com.evo.cms.service.pay.ServerRuleRelService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServerRelServiceImpl extends AbstractBaseService<ProductServerRel, String> implements ProductServerRelService {

    @Autowired
    private ProductServerRelRepository productServerRelRepository;

    @Override
    protected BaseRepository<ProductServerRel, String> getBaseRepository() {
        return productServerRelRepository;
    }

    @Autowired
    private ServerRuleRelService serverRuleRelService;


    @Override
    public void save(String objStr, String productId) {
        JSONArray ja = JSONArray.parseArray(objStr);
        for (int i = 0; i < ja.size(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            saveByProductIdAndServerRuleRelId(productId,jo.getString("id"));
        }
    }

    private void saveByProductIdAndServerRuleRelId(String productId, String serverRuleRelId) {
        ProductServerRel entity = this.getByProductIdAndServerRuleRelId(productId, serverRuleRelId);
        if (entity == null) {
            entity = new ProductServerRel();
            Product product = new Product();
            product.setId(productId);
            entity.setProduct(product);
            ServerRuleRel serverRuleRel = new ServerRuleRel();
            serverRuleRel.setId(serverRuleRelId);
            entity.setServerRuleRel(serverRuleRel);
            this.save(entity);
        }
    }

    @Override
    public ProductServerRel getByProductIdAndServerRuleRelId(String productId, String serverRuleId) {
        return productServerRelRepository.getByProductIdAndServerRuleRelId(productId, serverRuleId);
    }

    @Override
    public void sava(String productId, String serverId, String ruleId, String name) {
        ServerRuleRel serverRuleRel = new ServerRuleRel();
        serverRuleRel.setName(name);
        Server server= new Server();
        server.setId(serverId);
        serverRuleRel.setServer(server);
        Rule rule= new Rule();
        rule.setId(ruleId);
        serverRuleRel.setRule(rule);
        serverRuleRelService.save(serverRuleRel);
        saveByProductIdAndServerRuleRelId(productId,serverRuleRel.getId());
    }

    @Override
    public List<ProductServerRel> findByProductId(String id) {
        return productServerRelRepository.findByProductId(id);
    }

    @Override
    public void save(List<ProductServerRel> productServerRels) {
        for (ProductServerRel entity: productServerRels) {
            super.save(entity);
        }
    }

    @Override
    public List<ProductServerRel> findByServerRuleRelId(String serverRuleRelId) {
        return productServerRelRepository.findByServerRuleRelId(serverRuleRelId);
    }
}
