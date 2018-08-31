package com.f5.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.expression.Maps;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : wangtao
 * @date : 2018/8/16 16:22  星期四
 */
@Slf4j
@Configuration
public class ShiroConfiguration {
    /**
     * 将自己的验证方式加入容器
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //注册 密码算法
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 权限管理，配置主要是Realm的管理认证  可以加入缓存 CacheManager cacheManager,
     */
    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager(SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        //注册自定义的管理认证
        securityManager.setRealm(myShiroRealm());
//        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * <p>
     * 名称对应的过滤器 {@link org.apache.shiro.web.filter.mgt.DefaultFilter}
     * <p>
     * anon(AnonymousFilter.class),
     * authc(FormAuthenticationFilter.class),
     * authcBasic(BasicHttpAuthenticationFilter.class),
     * logout(LogoutFilter.class),
     * noSessionCreation(NoSessionCreationFilter.class),
     * perms(PermissionsAuthorizationFilter.class),
     * port(PortFilter.class),
     * rest(HttpMethodPermissionFilter.class),
     * roles(RolesAuthorizationFilter.class),
     * ssl(SslFilter.class),
     * user(UserFilter.class);
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<String, String>();
        //登出
        map.put("/logout", "logout");
        map.put("/favicon/*.ico", "anon");//对所有用户认证
        map.put("/**/*.html", "anon");//对所有用户认证
        map.put("/**/*.css", "anon");//对所有用户认证
        map.put("/**/*.js", "anon");//对所有用户认证
        map.put("/fonts/*", "anon");//对所有用户认证
        map.put("/images/*", "anon");//对所有用户认证
        map.put("/**", "authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("logout",new MyLogoutHandler());
        filterMap.put("anon",new MyAnonymousFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        Iterator<String> iterator = filterChainDefinitionMap.keySet().iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            log.debug("FilterChainDefinitionMap : {} --> {}", next, filterChainDefinitionMap.get(next));
        }
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        Iterator<String> iterator1 = filters.keySet().iterator();
        while (iterator1.hasNext()) {
            String next = iterator1.next();
            log.debug("Filter : {} --> {}", next, filters.get(next));
        }
        return shiroFilterFactoryBean;
    }

//    @Bean
//    public Filter myLogoutHandler() {
//        return new MyLogoutHandler();
//    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

//    @Bean
//    public CacheManager cacheManager() {
//        return new EhCacheManager();
//    }


    //    @Bean
//    public SessionDAO sessionDAO() {
//        return new EnterpriseCacheSessionDAO();
//    }

    /**
     * 自定义session的crud
     *
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {
        return new MySessionDAO();
    }

    /**
     * session manager 可以整合redis实现session共享
     *
     * @param sessionDAO
     * @return
     */
    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionDAO(sessionDAO);
        manager.setGlobalSessionTimeout(3600000);
        manager.setSessionValidationInterval(3600000);
        return manager;
    }

    /**
     * 密码加密算法
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }
}
