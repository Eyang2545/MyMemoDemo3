package com.mymemo.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mymemo.demo.mapper.UserActionMapper;
import com.mymemo.demo.mapper.UserMapper;
import com.mymemo.demo.model.User;
import com.mymemo.demo.model.UserAction;
import com.mymemo.demo.service.UserActionService;
import com.mymemo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActionServiceImpl extends ServiceImpl<UserActionMapper, UserAction> implements UserActionService {
    @Autowired
    private UserActionMapper userActionMapper;
    @Override
    public List<UserAction> getUserActionsByUsername(String username) {
        QueryWrapper<UserAction> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return userActionMapper.selectList(wrapper);
    }
}
