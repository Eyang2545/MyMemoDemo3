package com.mymemo.demo.config;

import com.mymemo.demo.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        myRealm.setCredentialsMatcher(matcher);
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }
    //拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //设置无需认证的范围
        definition.addPathDefinition("/user/userLogin","anon");
        definition.addPathDefinition("login","anon");
        //设置需要认证的范围
        definition.addPathDefinition("/**","authc");
        return definition;
    }
}
