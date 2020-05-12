package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfig;
import cn.com.evo.cms.domain.entity.pay.ProductPayTypeRel;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ProductPayTypeRelService extends BaseService<ProductPayTypeRel, String> {

    List<PayConfig> findByProductId(String productId);
}
