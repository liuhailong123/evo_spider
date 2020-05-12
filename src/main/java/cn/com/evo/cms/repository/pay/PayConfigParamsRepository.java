package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfigParams;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface PayConfigParamsRepository extends BaseRepository<PayConfigParams, String> {

    List<PayConfigParams> findByConfigId(String configId);
}
