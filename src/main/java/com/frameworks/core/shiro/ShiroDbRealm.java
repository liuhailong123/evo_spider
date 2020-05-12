package com.frameworks.core.shiro;

import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.web.controller.CaptchaController;
import com.frameworks.utils.Encodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountLockedException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


public class ShiroDbRealm extends AuthorizingRealm {
    private static final Logger logger = LogManager.getLogger(ShiroDbRealm.class);

    //private static final int STATUS_UNLOCKED = 0;
    private static final int STATUS_LOCKED = 1;


    // 是否启用超级管理员
    protected boolean activeRoot = false;

    // 是否使用验证码
    protected boolean useCaptcha = false;

    @Autowired
    protected ShiroDbService shiroDbService;

    /**
     * 给ShiroDbRealm提供编码信息，用于密码密码比对
     * 描述
     */
    public ShiroDbRealm() {
        super();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HashPassword.ALGORITHM);
        matcher.setHashIterations(HashPassword.INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 认证回调函数, 登录时调用.
     */
    // TODO 对认证进行缓存处理
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        if (useCaptcha) {
            CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
            String parm = token.getCaptcha();

            if (!CaptchaController.validate(SecurityUtils.getSubject().getSession().getId().toString(), parm.toLowerCase())) {//忽略大小写。
                throw new IncorrectCaptchaException("验证码错误！");
            }
        }

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        ShiroUser shiroUser = shiroDbService.findByUsername(token.getUsername());
        if (shiroUser.getAccount() != null) {
            if (shiroUser.getAccount().getStatus() == STATUS_LOCKED) {
                try {
                    throw new AccountLockedException("");
                } catch (AccountLockedException e) {
                    logger.error(e, e);
                }
            }

            byte[] salt = Encodes.decodeHex(shiroUser.getAccount().getSalt());

            // 这里可以缓存认证
            return new SimpleAuthenticationInfo(shiroUser, shiroUser.getAccount().getPassword(), ByteSource.Util.bytes(salt), getName());
        } else {
            return null;
        }

    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Collection<?> collection = principals.fromRealm(getName());
        if (collection == null || collection.isEmpty()) {
            return null;
        }

        ShiroUser shiroUser = (ShiroUser) collection.iterator().next();
        // 设置、更新User
        shiroUser.setAccount(shiroDbService.findById(shiroUser.getId()).getAccount());
        return newAuthorizationInfo(shiroUser);
    }

    private SimpleAuthorizationInfo newAuthorizationInfo(ShiroUser shiroUser) {
        Collection<String> hasPermissions = null;
        Collection<String> hasRoles = null;

        // 是否启用超级管理员 && 超级管理员，构造所有权限 ShiroConsts中定义的Super_Man
        if (activeRoot && shiroUser.getAccount().getSuperman() == ShiroConsts.SUPER_MAN) {
            hasRoles = new HashSet<String>();
            List<Role> roles = shiroDbService.findAll();

            for (Role role : roles) {
                hasRoles.add(role.getCode());
            }

            hasPermissions = new HashSet<String>();
            hasPermissions.add("*");

            if (logger.isInfoEnabled()) {
                logger.info("使用了" + shiroUser.getLoginName() + "的超级管理员权限:" + "。At " + new Date());
                logger.info(shiroUser.getLoginName() + "拥有的角色:" + hasRoles);
                logger.info(shiroUser.getLoginName() + "拥有的权限:" + hasPermissions);
            }
        } else {
            Collection<Role> roles = shiroDbService.findByShiroUser(shiroUser);
            hasRoles = shiroDbService.makeRoles(roles, shiroUser);
            hasPermissions = shiroDbService.makePermissions(roles, shiroUser);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(hasRoles);
        info.addStringPermissions(hasPermissions);

        return info;
    }

    /*
     * 覆盖父类方法，设置AuthorizationCacheKey为ShiroUser的loginName，这样才能顺利删除shiro中的缓存。
     * 因为shiro默认的AuthorizationCacheKey为PrincipalCollection的对象。
     * @see org.apache.shiro.realm.AuthorizingRealm#getAuthorizationCacheKey(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        return shiroUser.getLoginName();
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String loginName) {
        ShiroUser shiroUser = new ShiroUser(loginName);

        SimplePrincipalCollection principals = new SimplePrincipalCollection(shiroUser, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    /**
     * 设置 isActiveRoot 的值
     *
     * @param activeRoot
     */
    public void setActiveRoot(boolean activeRoot) {
        this.activeRoot = activeRoot;
    }

    /**
     * 设置 useCaptcha 的值
     *
     * @param useCaptcha
     */
    public void setUseCaptcha(boolean useCaptcha) {
        this.useCaptcha = useCaptcha;
    }
}
