package cn.com.evo.admin.manage.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.shiro.ShiroDbService;
import com.frameworks.core.shiro.ShiroUser;
import com.frameworks.core.web.constants.WebConsts;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.AccountRole;
import cn.com.evo.admin.manage.domain.entity.Module;
import cn.com.evo.admin.manage.domain.entity.OrganizationRole;
import cn.com.evo.admin.manage.domain.entity.Permission;
import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.domain.entity.RolePermission;
import cn.com.evo.admin.manage.repository.AccountRepository;
import cn.com.evo.admin.manage.repository.AccountRoleRepository;
import cn.com.evo.admin.manage.repository.OrganizationRoleRepository;
import cn.com.evo.admin.manage.repository.RoleRepository;

@Service
@Transactional
public class ShiroDbServiceImpl implements ShiroDbService {

    private static final Logger logger = LogManager.getLogger(ShiroDbServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private OrganizationRoleRepository organizationRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ShiroUser findById(String id) {
        Account account = accountRepository.findOne(id);
        ShiroUser user = new ShiroUser(account.getId(), account.getUsername());
        user.setAccount(account);
        return user;
    }

    @Override
    public ShiroUser findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        ShiroUser user = new ShiroUser(account.getId(), account.getUsername());
        user.setAccount(account);
        return user;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Collection<Role> findByShiroUser(ShiroUser shiroUser) {
        List<AccountRole> accountRoles = accountRoleRepository.findByAccountId(shiroUser.getId());
        List<OrganizationRole> organizationRoles = organizationRoleRepository
                .findByOrganizationId(shiroUser.getAccount().getOrganization().getId());
        return getUserRoles(accountRoles, organizationRoles);
    }

    /**
     * 组装角色权限
     * 
     * @param roles
     * @param shiroUser
     * @return
     */
    @Override
    public Collection<String> makeRoles(Collection<Role> roles, ShiroUser shiroUser) {
        Collection<String> hasRoles = new HashSet<String>();
        for (Role role : roles) {
            hasRoles.add(role.getCode());
        }

        if (logger.isInfoEnabled()) {
            logger.info(shiroUser.getLoginName() + "拥有的角色:" + hasRoles);
        }
        return hasRoles;
    }

    /**
     * 组装资源操作权限
     * 
     * @param roles
     * @param shiroUser
     * @return
     */
    @Override
    public Collection<String> makePermissions(Collection<Role> roles, ShiroUser shiroUser) {

        Collection<String> stringPermissions = Lists.newArrayList();
        for (Role role : roles) {
            List<RolePermission> rolePermissions = role.getRolePermissions();
            for (RolePermission rolePermission : rolePermissions) {
                Permission permission = rolePermission.getPermission();

                String operate = permission.getCode();
                String resource = getResource(permission.getModule(), operate);// permission.getModule().getCode();

                StringBuilder builder = new StringBuilder();
                builder.append(resource);

                stringPermissions.add(builder.toString());
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info(shiroUser.getLoginName() + "拥有的权限:" + stringPermissions);
        }
        return stringPermissions;
    }

    private String getResource(Module module, String resource) {
        if (module.getLevel() > (WebConsts.DEFAULT_ROOT_LEVEL + 1)) {
            resource = module.getCode() + ":" + resource;
            return getResource(module.getParent(), resource);
        } else {
            return module.getCode() + ":" + resource;
        }
    }

    private Collection<Role> getUserRoles(List<AccountRole> accountRoles, List<OrganizationRole> organizationRoles) {
        Set<Role> roles = new HashSet<Role>();
        for (AccountRole accountRole : accountRoles) {
            roles.add(accountRole.getRole());
        }

        for (OrganizationRole organizationRole : organizationRoles) {
            roles.add(organizationRole.getRole());
        }
        return roles;
    }

}
