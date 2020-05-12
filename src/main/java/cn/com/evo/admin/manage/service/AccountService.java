package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.Role;

public interface AccountService extends BaseService<Account, String> {
    List<Role> findRoles(Account entity);

    Account findByUsername(String username);

    void updatePassword(String id, String plainPassword, String newPassword);

    void resetPassword(String id, String newPassword);
}
