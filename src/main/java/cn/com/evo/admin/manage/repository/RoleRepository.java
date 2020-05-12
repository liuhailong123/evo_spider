package cn.com.evo.admin.manage.repository;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.Role;

public interface RoleRepository extends BaseRepository<Role, String> {
    Role findByCode(String code);

    List<Role> findByOrganizationId(String organizationId);
}
