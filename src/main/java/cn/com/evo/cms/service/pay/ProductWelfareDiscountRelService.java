package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.ProductWelfareDiscountRel;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ProductWelfareDiscountRelService extends BaseService<ProductWelfareDiscountRel, String> {

    List<ProductWelfareDiscountRel> findByProductIdAndType(String productId, int type);
}
