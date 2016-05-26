package com.predatum.sbsla.configuration;

import com.predatum.sbsla.auth.ShiroRealm;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroConfiguration
{

    private static final Map<String, String> FILTER_CHAIN_DEFINITION_MAP = new LinkedHashMap<>();


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {

        return new LifecycleBeanPostProcessor();
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager()
    {
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setCacheManager(cacheManager);
        defaultWebSecurityManager.setRealm(jdbcRealm());

        return defaultWebSecurityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/forbidden");
        FILTER_CHAIN_DEFINITION_MAP.put("/", "anon");
        FILTER_CHAIN_DEFINITION_MAP.put("/login", "anon");
        FILTER_CHAIN_DEFINITION_MAP.put("/ladmin/*", "user,roles[admin]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(FILTER_CHAIN_DEFINITION_MAP);
        Map<String, Filter> servletFilters = new HashMap<>();
        servletFilters.put("anon", new AnonymousFilter());
        servletFilters.put("authc", new FormAuthenticationFilter());
        servletFilters.put("logout", new LogoutFilter());
        servletFilters.put("roles", new RolesAuthorizationFilter());
        servletFilters.put("user", new UserFilter());
        shiroFilterFactoryBean.setFilters(servletFilters);

        return shiroFilterFactoryBean;
    }

    @Bean(name = "jdbcRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public JdbcRealm jdbcRealm()
    {
        JdbcRealm jdbcRealm = new ShiroRealm();
        jdbcRealm.setAuthenticationQuery("SELECT password, salt FROM user WHERE email = ?");
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setUserRolesQuery("select roleName from userrole where email = ?");
        jdbcRealm.setPermissionsQuery("select permission from rolespermission where roleName = ?");
        HashedCredentialsMatcher hashedCredentialMatcher = new HashedCredentialsMatcher();
        hashedCredentialMatcher.setHashAlgorithmName("SHA-256");
        hashedCredentialMatcher.setHashIterations(1024);
        hashedCredentialMatcher.setStoredCredentialsHexEncoded(false);
        jdbcRealm.setCredentialsMatcher(hashedCredentialMatcher);
        jdbcRealm.init();

        return jdbcRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

}
