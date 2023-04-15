package com.mymemo.demo.realm;

import com.mymemo.demo.model.User;
import com.mymemo.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = principalCollection.getPrimaryPrincipal().toString();
        //1 创建对象 封装当前用户的角色，权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //2 调用角色信息
        User user = userService.getUserInfoByName(principal);
        String role = user.getUserRole().toString();
        //3 存储角色
        info.addRole(role);
        //4 返回信息
        return info;
    }
    //自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户身份信息
        String username = authenticationToken.getPrincipal().toString();
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
            return null;
        }
    }
}
