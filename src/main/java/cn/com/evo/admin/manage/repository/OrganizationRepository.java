package cn.com.evo.admin.manage.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.Organization;

public interface OrganizationRepository extends BaseRepository<Organization, String> {

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "org.hibernate.cacheRegion", value = "SystemCache") })
    List<Organization> findByParentId(String parentId);

    @Query(value="select * from sys_organization where code like ?",nativeQuery=true)
	List<Organization> findByCode(String code);
    
}
