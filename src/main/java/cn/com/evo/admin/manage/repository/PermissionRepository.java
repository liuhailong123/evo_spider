package cn.com.evo.admin.manage.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.Permission;

public interface PermissionRepository extends BaseRepository<Permission, String> {

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "org.hibernate.cacheRegion", value = "SystemCache") })
    List<Permission> findByModuleId(String moduleId);

    List<Permission> findByIdIn(String[] ids);

}
