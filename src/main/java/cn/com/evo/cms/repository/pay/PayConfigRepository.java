package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfig;
import com.frameworks.core.repository.BaseRepository;

public interface PayConfigRepository extends BaseRepository<PayConfig, String> {

    PayConfig getByPayTypeAndProvinceCodeAndEnable(Integer payType, String provinceCode, int enable);
}
