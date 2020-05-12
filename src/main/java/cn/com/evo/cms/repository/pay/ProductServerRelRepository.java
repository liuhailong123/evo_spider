package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.ProductServerRel;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface ProductServerRelRepository extends BaseRepository<ProductServerRel, String> {

    ProductServerRel getByProductIdAndServerRuleRelId(String productId, String serverRuleId);

    List<ProductServerRel> findByProductId(String productId);

    List<ProductServerRel> findByServerRuleRelId(String serverRuleRelId);
}
