package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfig;
import cn.com.evo.cms.domain.entity.pay.ProductPayTypeRel;
import cn.com.evo.cms.repository.pay.ProductPayTypeRelRepository;
import cn.com.evo.cms.service.pay.PayConfigService;
import cn.com.evo.cms.service.pay.ProductPayTypeRelService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luxin
 */
@Service
@Transactional
public class ProductPayTypeRelServiceImpl extends AbstractBaseService<ProductPayTypeRel, String> implements ProductPayTypeRelService {

    @Autowired
    private ProductPayTypeRelRepository repository;

    @Autowired
    private PayConfigService payConfigService;

    @Override
    protected BaseRepository<ProductPayTypeRel, String> getBaseRepository() {
        return repository;
    }

    @Override
    public void save(ProductPayTypeRel entity) {
        ProductPayTypeRel rel = repository.getByConfigIdAndAndProductId(entity.getConfigId(), entity.getProductId());
        if (rel == null) {
            super.save(entity);
        } else {
            throw new RuntimeException("重复添加！！！");
        }
    }

    @Override
    public List<PayConfig> findByProductId(String productId) {
        List<PayConfig> list = Lists.newArrayList();
        List<ProductPayTypeRel> rels = repository.findByProductId(productId);
        for (ProductPayTypeRel rel : rels) {
            PayConfig config = payConfigService.findById(rel.getConfigId());
            list.add(config);
        }
        return list;
    }
}
