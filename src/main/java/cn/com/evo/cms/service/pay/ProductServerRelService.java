package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.ProductServerRel;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ProductServerRelService extends BaseService<ProductServerRel, String> {

    void save(String objStr, String productId);

    ProductServerRel getByProductIdAndServerRuleRelId(String productId, String id);

    void sava(String productId, String serverId, String ruleId, String name);

    List<ProductServerRel> findByProductId(String id);

    void save(List<ProductServerRel> productServerRels);

    List<ProductServerRel> findByServerRuleRelId(String serverRuleRelId);
}
