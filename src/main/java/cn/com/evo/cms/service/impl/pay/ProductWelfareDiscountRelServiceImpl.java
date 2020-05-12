package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfigParams;
import cn.com.evo.cms.domain.entity.pay.ProductWelfareDiscountRel;
import cn.com.evo.cms.repository.pay.PayConfigParamsRepository;
import cn.com.evo.cms.repository.pay.ProductWelfareDiscountRelRepository;
import cn.com.evo.cms.service.pay.PayConfigParamsService;
import cn.com.evo.cms.service.pay.ProductWelfareDiscountRelService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductWelfareDiscountRelServiceImpl extends AbstractBaseService<ProductWelfareDiscountRel, String> implements ProductWelfareDiscountRelService {

    @Autowired
    private ProductWelfareDiscountRelRepository productWelfareDiscountRelRepository;

    @Override
    protected BaseRepository<ProductWelfareDiscountRel, String> getBaseRepository() {
        return productWelfareDiscountRelRepository;
    }

    @Override
    public List<ProductWelfareDiscountRel> findByProductIdAndType(String productId, int type) {

        return productWelfareDiscountRelRepository.findByProductIdAndType(productId, type);
    }
}
