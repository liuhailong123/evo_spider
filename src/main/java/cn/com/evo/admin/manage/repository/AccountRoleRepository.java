package cn.com.evo.admin.manage.repository;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.AccountRole;

public interface AccountRoleRepository extends BaseRepository<AccountRole, String> {

    List<AccountRole> findByAccountId(String accountId);

}
