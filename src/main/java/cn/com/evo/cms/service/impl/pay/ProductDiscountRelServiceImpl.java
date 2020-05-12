package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.ProductDiscountRel;
import cn.com.evo.cms.repository.pay.ProductDiscountRelRepository;
import cn.com.evo.cms.service.pay.ProductDiscountRelService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductDiscountRelServiceImpl extends AbstractBaseService<ProductDiscountRel, String> implements ProductDiscountRelService {

    @Autowired
    private ProductDiscountRelRepository repository;

    @Override
    protected BaseRepository<ProductDiscountRel, String> getBaseRepository() {
        return repository;
    }

    @Override
    public List<ProductDiscountRel> findByProductId(String productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public List<ProductDiscountRel> findByProductIdAndType(String productId, Integer type) {
        if (type == null) {
            return this.findByProductId(productId);
        }
        return repository.findByProductIdAndType(productId, type);
    }

    @Override
    public void deleteByProductId(String productId) {
        List<ProductDiscountRel> entities = this.findByProductId(productId);
        if (entities.size() > 0) {
            super.deleteByEntities(entities);
        }
    }
}
