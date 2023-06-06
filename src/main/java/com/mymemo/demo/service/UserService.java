package com.mymemo.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mymemo.demo.common.ExistCode;
import com.mymemo.demo.model.User;

import java.util.List;

public interface UserService extends IService<User> {
    User getUserInfoByName(String username);
    User getUserInfoByEmail(String email);
    User getUserInfoByPhone(String phone);

    void revisePwdByPwd(String username,String newMD5pwd);
}
