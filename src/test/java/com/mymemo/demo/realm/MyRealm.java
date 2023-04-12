package com.mymemo.demo.realm;

import com.mymemo.demo.model.User;
import com.mymemo.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户身份信息
        String username = authenticationToken.getPrincipal().toString();
        System.out.println(authenticationToken);
        //调用业务层获取用户信息
        User user = userService.getUserInfoByName(username);
        //非空判断，完成封装
        if (user !=null){
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    user.getUsername(),
                    user.getUserPassword(),
                    ByteSource.Util.bytes("salt"),
                    getName()
            );
            return info;
        }else {
            System.out.println("去你妈的 没找到");
            return null;
        }
    }
}
