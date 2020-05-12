package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.ProductPayTypeRel;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

/**
 * @author luxin
 */
public interface ProductPayTypeRelRepository extends BaseRepository<ProductPayTypeRel, String> {
    ProductPayTypeRel getByConfigIdAndAndProductId(String configId, String productId);

    List<ProductPayTypeRel> findByProductId(String productId);
}
