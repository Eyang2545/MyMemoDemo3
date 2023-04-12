package com.mymemo.demo;

import com.mymemo.demo.mapper.UserMapper;
import com.mymemo.demo.model.User;
import com.mymemo.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class Demo3ApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;
    @Test
    public void test() {

        String username="998";
        String pwd="123456";
        AuthenticationToken token = new UsernamePasswordToken(username,pwd);
        System.out.println(token);
        System.out.println(token.getCredentials());
        String usernameInToken = token.getPrincipal().toString();

        System.out.println(usernameInToken);

        User user = userService.getUserInfoByName(usernameInToken);

        if (user==null) System.out.println("去你妈的 没找打--test");

        System.out.println(user);
    }


}
