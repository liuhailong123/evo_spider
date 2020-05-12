package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.ProductDiscountRel;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ProductDiscountRelService extends BaseService<ProductDiscountRel, String> {
    /**
     * 根据产品id获取优惠列表
     *
     * @param productId
     * @return
     */
    List<ProductDiscountRel> findByProductId(String productId);

    /**
     * 根据产品id和优惠类型获取优惠列表
     *
     * @param productId
     * @param type
     * @return
     */
    List<ProductDiscountRel> findByProductIdAndType(String productId, Integer type);

    void deleteByProductId(String productId);
}
