package com.mymemo.demo.controller;

import com.mymemo.demo.common.BaseResponse;
import com.mymemo.demo.common.ErrorCode;
import com.mymemo.demo.common.ResultUtils;
import com.mymemo.demo.exception.BusinessException;
import com.mymemo.demo.model.User;
import com.mymemo.demo.model.vo.UserVO;
import com.mymemo.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "用户管理模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("获取所有用户")
    @GetMapping("/findAll")
    public BaseResponse<List<User>> findAllUser(){
        List<User> userList = userService.list();
        //List<UserVO> userVOList = userList.stream().map(User::toVo).collect(Collectors.toList());
        return ResultUtils.success(userList);
    }
    @ApiOperation("用户登录（密码）")
    @PostMapping("/userLogin")
    public BaseResponse<UserVO> userLogin(String username,String password){

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        System.out.println(token);
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            UserVO userVO = userService.getUserInfoByName(username).toVo();
            return ResultUtils.success(userVO);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultUtils.error(ErrorCode.NOT_LOGIN);
        }
    }
    @ApiOperation("用户注册")
    @PostMapping("/register")
    BaseResponse<UserVO> register(@RequestBody User user){
        if (userService.getUserInfoByName(user.getUsername())==null){
            String pwd = user.getUserPassword();
            String MD5pwd = new SimpleHash("md5",pwd,"salt",3).toString();
            user.setUserPassword(MD5pwd);
            if (userService.save(user))return ResultUtils.success(user.toVo());
            else return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
        }else return ResultUtils.error(ErrorCode.PARAMS_ERROR,"用户名已存在！","用户名已存在！");
    }
}
