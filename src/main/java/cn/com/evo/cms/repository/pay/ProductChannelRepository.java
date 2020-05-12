package cn.com.evo.cms.repository.pay;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.pay.ProductChannel;

public interface ProductChannelRepository extends BaseRepository<ProductChannel, String> {

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "org.hibernate.cacheRegion", value = "SystemCache") })
    List<ProductChannel> findByParentId(String parentId);

    @Query(value="select * from sys_organization where code like ?",nativeQuery=true)
	List<ProductChannel> findByCode(String code);
    
}
