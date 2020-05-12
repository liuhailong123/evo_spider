package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.exception.ExistedException;
import com.frameworks.core.exception.IncorrectPasswordException;
import com.frameworks.core.exception.NotAllowDeleteException;
import com.frameworks.core.exception.NotAllowUpdateException;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.shiro.HashPassword;
import com.frameworks.core.shiro.ShiroConsts;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.AccountRole;
import cn.com.evo.admin.manage.domain.entity.OrganizationRole;
import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.repository.AccountRepository;
import cn.com.evo.admin.manage.repository.AccountRoleRepository;
import cn.com.evo.admin.manage.repository.OrganizationRoleRepository;
import cn.com.evo.admin.manage.repository.RoleRepository;
import cn.com.evo.admin.manage.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl extends AbstractBaseService<Account, String> implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrganizationRoleRepository organizationRoleRepository;

    @Override
    public List<Role> findRoles(Account entity) {
        List<Role> roles = Lists.newArrayList();
        if (this.isSuperman(entity)) {
            roles = roleRepository.findAll();
        } else {
            List<AccountRole> accountRoles = accountRoleRepository.findByAccountId(entity.getId());
            for (AccountRole accountRole : accountRoles) {
                Role e = accountRole.getRole();
                if (!roles.contains(e)) {
                    roles.add(e);
                }
            }
            List<OrganizationRole> orgRoles = organizationRoleRepository
                    .findByOrganizationId(entity.getOrganization().getId());
            for (OrganizationRole organizationRole : orgRoles) {
                Role e = organizationRole.getRole();
                if (!roles.contains(e)) {
                    roles.add(e);
                }
            }
        }
        return roles;
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void updatePassword(String id, String plainPassword, String newPassword) {
        Account entity = this.findById(id);
        boolean isMatch = HashPassword.getMe().validatePassword(plainPassword, entity.getPassword(), entity.getSalt());
        if (isMatch) {
            HashPassword hashPassword = HashPassword.getMe().encryptPassword(newPassword);
            entity.setSalt(hashPassword.getSalt());
            entity.setPassword(hashPassword.getPassword());
            this.saveOrUpdate(entity);
        } else {
            throw new IncorrectPasswordException("原始密码不正确!");
        }
    }

    @Override
    public void resetPassword(String id, String newPassword) {
        if (StringUtils.isBlank(newPassword)) {
            newPassword = "123456";
        }
        Account entity = this.findById(id);
        if (isSuperman(entity)) {
            throw new NotAllowUpdateException("不允许重置超级用户的密码, 请通过超级用户个人页面进行修改.");
        }
        HashPassword hashPassword = HashPassword.getMe().encryptPassword(newPassword);
        entity.setSalt(hashPassword.getSalt());
        entity.setPassword(hashPassword.getPassword());
        this.saveOrUpdate(entity);
    }

    @Override
    public void save(Account entity) {
        Account dbAccount = this.findByUsername(entity.getUsername());
        if (null != dbAccount) {
            throw new ExistedException("用户登录名" + entity.getUsername() + "已存在");
        }
        String pwd = "123456";
        if (StringUtils.isNotBlank(entity.getPassword())) {
            pwd = entity.getPassword();
        }
        HashPassword hashPassword = HashPassword.getMe().encryptPassword(pwd);
        entity.setSalt(hashPassword.getSalt());
        entity.setPassword(hashPassword.getPassword());
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(Account entity) {
        Account account = super.findById(entity.getId());
        // 设定安全的密码
        if (StringUtils.isNotBlank(entity.getPlainPassword())) {
            HashPassword hashPassword = HashPassword.getMe().encryptPassword(entity.getPlainPassword());
            entity.setSalt(hashPassword.getSalt());
            entity.setPassword(hashPassword.getPassword());
        } else {
            entity.setPassword(account.getPassword());
            entity.setSalt(account.getSalt());
        }
        entity.setAccountRoles(account.getAccountRoles());
        entity.setCreateDate(account.getCreateDate());
        super.saveOrUpdate(entity);
    }

    @Override
    public void deleteById(String id) {
        Account dbAccount = this.findById(id);
        if (isSuperman(dbAccount)) {
            throw new NotAllowDeleteException("不允许删除超级用户.");
        }
        super.deleteById(id);
    }

    @Override
    public void deleteByEntity(Account entity) {
        if (isSuperman(entity)) {
            throw new NotAllowDeleteException("不允许删除超级用户.");
        }
        super.deleteByEntity(entity);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            Account dbAccount = this.findById(id);
            if (isSuperman(dbAccount)) {
                throw new NotAllowDeleteException("不允许删除超级用户.");
            }
        }
        super.deleteByIds(ids);
    }

    @Override
    public void deleteByEntities(Iterable<Account> entities) {
        for (Account account : entities) {
            if (isSuperman(account)) {
                throw new NotAllowDeleteException("不允许删除超级用户.");
            }
        }
        super.deleteByEntities(entities);
    }

    @Override
    protected BaseRepository<Account, String> getBaseRepository() {
        return accountRepository;
    }

    private boolean isSuperman(Account entity) {
        return entity.getSuperman() == ShiroConsts.SUPER_MAN;
    }
}
