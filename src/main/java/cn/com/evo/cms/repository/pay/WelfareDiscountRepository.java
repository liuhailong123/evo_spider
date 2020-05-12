package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.WelfareDiscount;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface WelfareDiscountRepository extends BaseRepository<WelfareDiscount, String> {

    @Query("select max(d.code) from WelfareDiscount d")
    Integer getMaxCode();
}
