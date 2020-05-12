package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfig;
import cn.com.evo.cms.repository.pay.PayConfigRepository;
import cn.com.evo.cms.service.pay.PayConfigService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PayConfigServiceImpl extends AbstractBaseService<PayConfig, String> implements PayConfigService {

    @Autowired
    private PayConfigRepository payConfigRepository;

    @Override
    protected BaseRepository<PayConfig, String> getBaseRepository() {
        return payConfigRepository;
    }

    @Override
    public PayConfig getByPayTypeAndProvinceCodeAndEnable(Integer payType, String provinceCode, int enable) {
        return payConfigRepository.getByPayTypeAndProvinceCodeAndEnable(payType, provinceCode, enable);
    }

    @Override
    public void save(PayConfig entity) {
        if (entity.getEnable() == 1) {
            PayConfig temp = getByPayTypeAndProvinceCodeAndEnable(entity.getPayType(),
                    entity.getProvinceCode(), entity.getEnable());
            if(temp != null){
                throw new RuntimeException("已存在启用的该支付方式");
            }
        }

        super.save(entity);
    }

    @Override
    public void update(PayConfig entity) {
        if (entity.getEnable() == 1) {
            PayConfig temp = getByPayTypeAndProvinceCodeAndEnable(entity.getPayType(),
                    entity.getProvinceCode(), entity.getEnable());
            if(temp != null){
                throw new RuntimeException("已存在启用的该支付方式");
            }
        }

        super.update(entity);
    }
}
