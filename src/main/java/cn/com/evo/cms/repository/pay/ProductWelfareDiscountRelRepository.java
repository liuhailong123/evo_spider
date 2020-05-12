package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.ProductWelfareDiscountRel;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface ProductWelfareDiscountRelRepository extends BaseRepository<ProductWelfareDiscountRel, String> {
    List<ProductWelfareDiscountRel> findByProductIdAndType(String productId, int type);
}
