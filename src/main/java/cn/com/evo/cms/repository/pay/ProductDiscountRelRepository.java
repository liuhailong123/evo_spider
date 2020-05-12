package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.ProductDiscountRel;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface ProductDiscountRelRepository extends BaseRepository<ProductDiscountRel, String> {
    List<ProductDiscountRel> findByProductId(String productId);

    List<ProductDiscountRel> findByProductIdAndType(String productId, Integer type);
}
