package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.pay.ProductRel;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ProductRelService extends BaseService<ProductRel, String> {

    /**
     * 根据业务外键id删除数据
     *
     * @param bizId 业务id
     */
    void deleteByBizId(String bizId);

    /**
     * 根据业务id获取产品套餐列表
     *
     * @param bizId 业务id
     * @return
     */
    List<Product> findByBizId(String bizId);

    /**
     * 根据业务id和是否显示字段获取产品套餐 并按照sort排正序
     *
     *
     * @param bizId
     * @param isShow
     * @return
     */
    List<Product> findByBizIdAndIsShowOrderBySort(String bizId,Integer isShow);

    /**
     * 根据业务id、产品类型获取产品套餐列表
     *
     * @param bizId
     * @param type
     * @return
     */
    List<Product> findByBizIdAndType(String bizId, Integer type);

    /**
     * 根据业务id、产品类型、是否显示获取产品套餐列表
     *
     * @param bizId
     * @param type
     * @return
     */
    List<Product> findByBizIdAndTypeAndIsShow(String bizId, Integer type, Integer isShow);

    /**
     * 根据产品id和业务id获取定价对象
     *
     * @param productId
     * @param bizId
     * @return
     */
    ProductRel getByProductIdAndBizId(String productId, String bizId);
}
