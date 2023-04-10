package com.mymemo.demo.controller;

import com.mymemo.demo.common.BaseResponse;
import com.mymemo.demo.common.ResultUtils;
import com.mymemo.demo.model.User;
import com.mymemo.demo.model.vo.UserVO;
import com.mymemo.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "用户管理模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("获取所有用户")
    @GetMapping("findAll")
    public BaseResponse<List<UserVO>> findAllUser(){
        List<User> userList = userService.list();
        List<UserVO> userVOList = userList.stream().map(User::toVo).collect(Collectors.toList());
        return ResultUtils.success(userVOList);
    }
}
