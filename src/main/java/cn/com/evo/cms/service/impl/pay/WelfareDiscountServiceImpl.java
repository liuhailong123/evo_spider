package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.WelfareDiscount;
import cn.com.evo.cms.repository.pay.WelfareDiscountRepository;
import cn.com.evo.cms.service.pay.WelfareDiscountService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WelfareDiscountServiceImpl extends AbstractBaseService<WelfareDiscount, String> implements WelfareDiscountService {

    @Autowired
    private WelfareDiscountRepository repository;

    @Override
    protected BaseRepository<WelfareDiscount, String> getBaseRepository() {
        return repository;
    }

    @Override
    public void save(WelfareDiscount entity) {
        entity.setCode(getCode().toString());
        super.save(entity);
    }


    public Integer getCode() {
        Integer code = repository.getMaxCode();
        if (code == null) {
            return 1;
        } else {
            return code + 1;
        }
    }
}
