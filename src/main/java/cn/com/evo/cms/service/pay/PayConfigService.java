package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.constant.ProvinceCodeEnum;
import cn.com.evo.cms.domain.entity.pay.PayConfig;
import com.frameworks.core.service.BaseService;

public interface PayConfigService extends BaseService<PayConfig, String> {
    /**
     * 根据 支付方式 省网编码 是否启用 获取支付配置
     *
     * @param payType
     * @param provinceCode
     * @param enable
     * @return
     */
    PayConfig getByPayTypeAndProvinceCodeAndEnable(Integer payType, String provinceCode, int enable);
}
