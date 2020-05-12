package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfigParams;
import cn.com.evo.cms.repository.pay.PayConfigParamsRepository;
import cn.com.evo.cms.service.pay.PayConfigParamsService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PayConfigParamsServiceImpl extends AbstractBaseService<PayConfigParams, String> implements PayConfigParamsService {

    @Autowired
    private PayConfigParamsRepository payConfigParamsRepository;

    @Override
    protected BaseRepository<PayConfigParams, String> getBaseRepository() {
        return payConfigParamsRepository;
    }

    @Override
    public List<PayConfigParams> findByConfigId(String configId) {

        return payConfigParamsRepository.findByConfigId(configId);
    }
}
