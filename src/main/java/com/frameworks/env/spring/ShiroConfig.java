package com.frameworks.env.spring;

import com.frameworks.core.shiro.AjaxRequestFilter;
import com.frameworks.core.shiro.BaseFormAuthenticationFilter;
import com.frameworks.core.shiro.CaptchaFormAuthenticationFilter;
import com.frameworks.core.shiro.ShiroDbRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import javax.servlet.Filter;
import java.util.Map;

@Configuration
@Description(value = "Shiro 安全基础配置")
public class ShiroConfig {

    private static final boolean IS_USE_CAPTCHA = true;
    private static final boolean IS_ACTIVE_ROOT = true;

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroDbRealm());
        securityManager.setCacheManager(shiroEhcacheManager());
        return securityManager;
    }

    @Bean
    public ShiroDbRealm shiroDbRealm() {
        ShiroDbRealm shiroDbRealm = new ShiroDbRealm();
        shiroDbRealm.setUseCaptcha(IS_USE_CAPTCHA);
        shiroDbRealm.setActiveRoot(IS_ACTIVE_ROOT);
        return shiroDbRealm;
    }

    /**
     * 用户授权信息Cache, 采用EhCache
     *
     * @return
     */
    @Bean
    public EhCacheManager shiroEhcacheManager() {
        EhCacheManager shiroEhcacheManager = new EhCacheManager();
        shiroEhcacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return shiroEhcacheManager;
    }

    @Bean
    public BaseFormAuthenticationFilter baseFormAuthenticationFilter() {
        return new BaseFormAuthenticationFilter();
    }

    @Bean
    public CaptchaFormAuthenticationFilter captchaFormAuthenticationFilter() {
        return new CaptchaFormAuthenticationFilter();
    }

    @Bean
    public AjaxRequestFilter ajaxRequestFilter() {
        return new AjaxRequestFilter();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/manage");
        shiroFilter.setUnauthorizedUrl("/login");

        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/site/**", "anon");
        filterChainDefinitionMap.put("/captcha", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/ueditor/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/login/timeout", "anon");
        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "ajax");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        Map<String, Filter> mapFilters = Maps.newHashMap();
        if (!IS_USE_CAPTCHA) {
            mapFilters.put("authc", baseFormAuthenticationFilter());
        } else {
            mapFilters.put("authc", captchaFormAuthenticationFilter());
        }
        mapFilters.put("ajax", ajaxRequestFilter());
        shiroFilter.setFilters(mapFilters);
        return shiroFilter;
    }


    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
