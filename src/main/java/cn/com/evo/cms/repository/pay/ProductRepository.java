package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.Product;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product, String> {

    Product getByCode(String code);

    List<Product> findByAppIdOrderBySort(String appId);

    Product getByThirdPartyCodeAndCode(String prodCode, String code);

    Product getByThirdPartyCode(String prodCode);

    /**
     * 根据产品编码和服务编码获取产品套餐
     *
     * @param prodCode   产品编码
     * @param serverCode 服务编码
     * @return
     */
    @Query(value = "SELECT p.* FROM pay_product p,pay_product_server_rel sr,pay_server_rule_rel rr,pay_server s WHERE sr.product_id=p.id AND sr.server_rule_rel_id=rr.id AND rr.server_id=s.id AND s.CODE=?2 AND p.CODE=?1", nativeQuery = true)
    Product getByProductCodeAndServerCode(String prodCode, String serverCode);

}
