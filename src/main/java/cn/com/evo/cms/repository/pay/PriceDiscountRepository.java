package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.PriceDiscount;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceDiscountRepository extends BaseRepository<PriceDiscount, String> {

    @Query("select max(d.code) from PriceDiscount d")
    Integer getMaxCode();
}
