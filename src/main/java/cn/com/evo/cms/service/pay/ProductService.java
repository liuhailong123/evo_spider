package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.Product;
import com.frameworks.core.service.BaseService;

import java.util.Date;
import java.util.List;

public interface ProductService extends BaseService<Product, String> {
    List<Product> findByAppId(String appId);

    Product getByCode(String prodCode);

    /**
     * 根据产品id计算优惠价
     *
     * @param ids
     */
    void calculateByIds(String[] ids);

    /**
     * 根据三方产品编码查询产品信息
     * @param prodCode
     * @return
     */
    Product getByThirdPartyCodeAndCode(String prodCode, String code);

    /**
     * 根据三方产品编码查询产品信息
     * @param prodCode
     * @return
     */
    Product getByThirdPartyCode(String prodCode);

    /**
     * 产品是否包含图书服务
     *
     * @param prodCode 产品编码
     * @return true-包含；false-不包含
     */
    boolean hasBookServer(String prodCode);

    /**
     * 根据某时间和某产品获取结束时间
     *
     * @param beginDate   开始时间
     * @param productCode 产品编码
     * @return 结束时间
     */
    Date getOverDate(Date beginDate, String productCode);

}
