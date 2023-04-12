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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "用户管理模块")
@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("获取所有用户")
    @GetMapping("/findAll")
    @ResponseBody
    public BaseResponse<List<User>> findAllUser(){
        List<User> userList = userService.list();
        //List<UserVO> userVOList = userList.stream().map(User::toVo).collect(Collectors.toList());
        return ResultUtils.success(userList);
    }
    @ApiOperation("用户登录（密码）")
    @GetMapping("/userLogin")
    public String userLogin(String username, String password, HttpSession session){
        System.out.println(username);
        System.out.println(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        System.out.println(token);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            UserVO userVO = userService.getUserInfoByName(username).toVo();
            session.setAttribute("user",userVO);
            return "main.html";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "";
        }
    }
    @ApiOperation("用户注册")
    @PostMapping("/userRegister")
    @ResponseBody
    BaseResponse<UserVO> register(@RequestBody User user){
        if (userService.getUserInfoByName(user.getUsername())==null){
            String pwd = user.getUserPassword();
            String MD5pwd = new SimpleHash("md5",pwd,"salt",3).toString();
            user.setUserPassword(MD5pwd);
            if (userService.save(user))return ResultUtils.success(user.toVo());
            else return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
        }else return ResultUtils.error(ErrorCode.PARAMS_ERROR,"用户名已存在！","用户名已存在！");
    }
    @ApiOperation("删除用户（username）")
    @ResponseBody
    @DeleteMapping("/userDelete")
    public BaseResponse<UserVO> delete(String username){
        User user = userService.getUserInfoByName(username);
        if(user!=null){
            if (userService.removeById(user.getId()))return ResultUtils.success(user.toVo());
            else return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
        }else return ResultUtils.error(ErrorCode.PARAMS_ERROR,"没有这个用户","没有这个用户");
    }



}
