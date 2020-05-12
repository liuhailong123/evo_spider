package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.admin.manage.domain.entity.OrganizationRole;
import cn.com.evo.admin.manage.repository.OrganizationRoleRepository;
import cn.com.evo.admin.manage.service.OrganizationRoleService;

@Service
@Transactional
public class OrganizationRoleServiceImpl extends AbstractBaseService<OrganizationRole, String>
        implements OrganizationRoleService {

    @Autowired
    private OrganizationRoleRepository organizationRoleRepository;

    @Override
    public void save(OrganizationRole entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(OrganizationRole entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public List<OrganizationRole> findByOrganizationId(String organizationId) {
        return organizationRoleRepository.findByOrganizationId(organizationId);
    }

    @Override
    protected BaseRepository<OrganizationRole, String> getBaseRepository() {
        return organizationRoleRepository;
    }

}
