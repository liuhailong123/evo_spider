package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfigParams;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface PayConfigParamsService extends BaseService<PayConfigParams, String> {
    List<PayConfigParams> findByConfigId(String configId);
}
