package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.pay.ProductRel;
import cn.com.evo.cms.repository.pay.ProductRelRepository;
import cn.com.evo.cms.service.pay.ProductRelService;
import cn.com.evo.cms.service.pay.ProductService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductRelServiceImpl extends AbstractBaseService<ProductRel, String> implements ProductRelService {

    @Autowired
    private ProductRelRepository productRelRepository;
    @Autowired
    private ProductService productService;


    @Override
    protected BaseRepository<ProductRel, String> getBaseRepository() {
        return productRelRepository;
    }

    @Override
    public void save(ProductRel entity) {
        ProductRel temp = productRelRepository.getByBizIdAndProductId(entity.getBizId(), entity.getProductId());
        if (temp == null) {
            super.save(entity);
        } else {
            throw new RuntimeException("请勿重复添加");
        }
    }

    @Override
    public void deleteByBizId(String bizId) {
        List<ProductRel> list = productRelRepository.findByBizId(bizId);
        if (list.size() > 0) {
            this.deleteByEntities(list);
        } else {
            throw new RuntimeException("暂无定价");
        }
    }

    @Override
    public List<Product> findByBizId(String bizId) {
        List<Product> products = Lists.newArrayList();
        List<ProductRel> list = productRelRepository.findByBizId(bizId);
        for (ProductRel productRel : list) {
            Product product = productService.findById(productRel.getProductId());
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> findByBizIdAndIsShowOrderBySort(String bizId, Integer isShow) {
        List<Product> products = Lists.newArrayList();
        List<ProductRel> list = productRelRepository.findByBizIdAndIsShowOrderBySort(bizId, isShow);
        for (ProductRel productRel : list) {
            Product product = productService.findById(productRel.getProductId());
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> findByBizIdAndType(String bizId, Integer type) {
        List<Product> products = Lists.newArrayList();
        List<ProductRel> list = productRelRepository.findByBizId(bizId);
        for (ProductRel productRel : list) {
            Product product = productService.findById(productRel.getProductId());
            if (type.equals(product.getType())) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public List<Product> findByBizIdAndTypeAndIsShow(String bizId, Integer type, Integer isShow) {
        List<Product> products = Lists.newArrayList();
        List<ProductRel> list = productRelRepository.findByBizIdAndIsShowOrderBySort(bizId, isShow);
        for (ProductRel productRel : list) {
            Product product = productService.findById(productRel.getProductId());
            if (type.equals(product.getType())) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public ProductRel getByProductIdAndBizId(String productId, String bizId) {
        return productRelRepository.getByBizIdAndProductId(bizId, productId);
    }
}
