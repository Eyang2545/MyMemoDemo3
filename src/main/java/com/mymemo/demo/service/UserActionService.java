package com.mymemo.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mymemo.demo.model.User;
import com.mymemo.demo.model.UserAction;

import java.util.List;

public interface UserActionService extends IService<UserAction> {
    List<UserAction> getUserActionsByUsername(String username);
}
