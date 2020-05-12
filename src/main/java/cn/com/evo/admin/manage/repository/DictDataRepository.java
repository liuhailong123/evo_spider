package cn.com.evo.admin.manage.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.DictData;

public interface DictDataRepository extends BaseRepository<DictData, String> {

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "org.hibernate.cacheRegion", value = "SystemCache") })
    List<DictData> findByDictClassifyId(String classifyId);

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "org.hibernate.cacheRegion", value = "SystemCache") })
    @Query("from DictData d where d.dictClassify.code=?1 order by d.priority asc")
    List<DictData> findByDictClassifyCode(String classifyCode);
    
    DictData getByDictClassifyCodeAndCode(String classifyCode,String code);
}
