package cn.com.evo.cms.repository.cms;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.cms.Active;
import org.springframework.data.jpa.repository.Query;

public interface ActiveRepository extends BaseRepository<Active, String> {

    @Query(value = "SELECT * from cms_active_info where id=?1 and validTime <= ?2 and unValidTime >=?2", nativeQuery = true)
    Active getByIdAndThisTime(String id, String thisTime);
}
