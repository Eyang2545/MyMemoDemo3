package com.mymemo.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mymemo.demo.mapper.UserMapper;
import com.mymemo.demo.model.User;
import com.mymemo.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
