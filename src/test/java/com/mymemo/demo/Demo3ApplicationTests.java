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
    @Test
    public void findAll() {
        List<User> list=userMapper.selectList(null);
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void add() {
        User user = new User();
        user.setUsername("Ohana");
        user.setUserAccount("2545");
        user.setAvatarUrl("kawayi");
        user.setGender(3);
        user.setUserPassword("123");
        user.setPhone("153");
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }
    @Autowired
    UserService userService;
    @Test
    public void findAll1() {
        List<User> list = userService.list();
        System.out.println(list);
    }


}
