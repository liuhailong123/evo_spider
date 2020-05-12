package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.Organization;

public interface OrganizationService extends BaseService<Organization, String> {

    List<Organization> findByParentId(String parentId);

	List<Organization> findByAccount(Account account);
}
