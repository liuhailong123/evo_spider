package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.Rule;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RuleRepository extends BaseRepository<Rule, String> {

    /**
     * 根据产品code和服务code获取规则列表
     *
     * @param productCode 产品code
     * @param serverCode  服务code(10001-视频点播；10002-绘本借阅；10003-绘本配送)
     * @return
     */
    @Query(value = "SELECT r.*FROM pay_product p,pay_product_server_rel psr,pay_server_rule_rel srr,pay_rule r,pay_server s WHERE p.id=psr.product_id AND psr.server_rule_rel_id=srr.id AND r.id=srr.rule_id AND s.id=srr.server_id AND p.CODE=? AND s.CODE=?", nativeQuery = true)
    List<Rule> findByProductCodeAndServerCode(String productCode, String serverCode);
}
