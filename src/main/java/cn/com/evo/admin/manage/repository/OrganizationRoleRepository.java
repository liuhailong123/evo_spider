package cn.com.evo.admin.manage.repository;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.OrganizationRole;

public interface OrganizationRoleRepository extends BaseRepository<OrganizationRole, String> {

    List<OrganizationRole> findByOrganizationId(String organizationId);

}
