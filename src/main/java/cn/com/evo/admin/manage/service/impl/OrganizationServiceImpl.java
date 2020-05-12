package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.web.constants.WebConsts;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.Organization;
import cn.com.evo.admin.manage.repository.OrganizationRepository;
import cn.com.evo.admin.manage.service.OrganizationService;

@Service
@Transactional
public class OrganizationServiceImpl extends AbstractBaseService<Organization, String> implements OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public void save(Organization entity) {
		if (entity.getParent().getId() == null) {
			entity.setParent(null);
		}
		super.saveOrUpdate(entity);
	}

	@Override
	public void update(Organization entity) {
		Organization organization = super.findById(entity.getId());
		if (entity.getLevel() == WebConsts.DEFAULT_ROOT_LEVEL) {
			entity.setParent(null);
		}
		entity.setChildren(organization.getChildren());
		entity.setOrganizationRoles(organization.getOrganizationRoles());
		entity.setCreateDate(organization.getCreateDate());
		super.saveOrUpdate(entity);
	}

	@Override
	public List<Organization> findByParentId(String parentId) {
		return organizationRepository.findByParentId(parentId);
	}

	@Override
	protected BaseRepository<Organization, String> getBaseRepository() {
		return organizationRepository;
	}

	@Override
	public List<Organization> findByAccount(Account account) {
		Organization organization = account.getOrganization();
		if (organization == null) {
			organization = this.findById("7");
		}
		List<Organization> organizations = organizationRepository.findByCode(organization.getCode() + "%");
		return organizations;
	}

}
