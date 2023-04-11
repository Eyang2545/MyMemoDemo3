package com.mymemo.demo;

import com.mymemo.demo.mapper.UserMapper;
import com.mymemo.demo.model.User;
import com.mymemo.demo.service.UserService;
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
        User user = userService.getUserInfoByName("997");
        System.out.println(user);
    }


}
