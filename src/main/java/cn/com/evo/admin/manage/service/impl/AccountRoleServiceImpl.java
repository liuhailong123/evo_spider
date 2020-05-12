package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.admin.manage.domain.entity.AccountRole;
import cn.com.evo.admin.manage.repository.AccountRoleRepository;
import cn.com.evo.admin.manage.service.AccountRoleService;

@Service
@Transactional
public class AccountRoleServiceImpl extends AbstractBaseService<AccountRole, String> implements AccountRoleService {

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public void save(AccountRole entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(AccountRole entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public List<AccountRole> findByAccountId(String accountId) {
        return accountRoleRepository.findByAccountId(accountId);
    }

    @Override
    protected BaseRepository<AccountRole, String> getBaseRepository() {
        return accountRoleRepository;
    }

}
