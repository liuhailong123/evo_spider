package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.Rule;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface RuleService extends BaseService<Rule, String> {

    /**
     * 根据产品code和服务code获取规则列表
     *
     * @param productCode 产品code
     * @param serverCode  服务code(10001-视频点播；10002-绘本借阅；10003-绘本配送)
     * @return
     */
    List<Rule> findByProductCodeAndServerCode(String productCode, String serverCode);
}
