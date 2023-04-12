package com.mymemo.demo.config;

import com.mymemo.demo.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean
    //配置securityManager
    public DefaultWebSecurityManager securityManager(){
        //创建对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //创建加密对象，设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(3);
        //将加密对象存入realm中
        myRealm.setCredentialsMatcher(matcher);
        //将realm存入manager中
        securityManager.setRealm(myRealm);
        return securityManager;
    }
    //拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //设置无需认证的范围
        definition.addPathDefinition("/api/userLogin","anon");
        definition.addPathDefinition("/index/login","anon");
        //设置需要认证的范围
        //definition.addPathDefinition("/**","authc");
        return definition;
    }


}
