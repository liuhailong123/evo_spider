package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.pay.ProductRel;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface ProductRelRepository extends BaseRepository<ProductRel, String> {

    List<ProductRel> findByBizId(String bizId);

    ProductRel getByBizIdAndProductId(String bizId, String productId);

    /**
     * 根据业务id和是否显示字段获取产品套餐 并按照sort排正序
     *
     *
     * @param bizId
     * @param isShow
     * @return
     */
    List<ProductRel> findByBizIdAndIsShowOrderBySort(String bizId, Integer isShow);
}
