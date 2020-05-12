package cn.com.evo.admin.manage.repository;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.Account;

public interface AccountRepository extends BaseRepository<Account, String> {

    Account findByUsername(String username);

    List<Account> findByOrganizationId(String organizationId);
    
    Account getByUsername(String username);
}
