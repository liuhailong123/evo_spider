package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.OrganizationRole;

public interface OrganizationRoleService extends BaseService<OrganizationRole, String> {

    List<OrganizationRole> findByOrganizationId(String organizationId);
}
