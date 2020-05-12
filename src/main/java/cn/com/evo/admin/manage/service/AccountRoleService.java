package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.AccountRole;

public interface AccountRoleService extends BaseService<AccountRole, String> {
    List<AccountRole> findByAccountId(String accountId);
}
