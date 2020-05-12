package cn.com.evo.admin.manage.repository;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.DictClassify;

public interface DictClassifyRepository extends BaseRepository<DictClassify, String> {

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "org.hibernate.cacheRegion", value = "SystemCache") })
    DictClassify findByCode(String code);
}
