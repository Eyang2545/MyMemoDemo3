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
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
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
    @RequiresRoles("1")
    public BaseResponse<List<User>> findAllUser() {
        List<User> userList = userService.list();
        //List<UserVO> userVOList = userList.stream().map(User::toVo).collect(Collectors.toList());
        return ResultUtils.success(userList);
    }

    @ApiOperation("用户登录（密码）")
    @PostMapping("/userLogin")
    @ResponseBody
    public BaseResponse<String> userLogin(String username, String password,
                                          @RequestParam(defaultValue = "false")boolean rememberMe,
                                          HttpSession session) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            UserVO userVO = userService.getUserInfoByName(username).toVo();
            session.setAttribute("user", userVO);
            return ResultUtils.success("登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultUtils.error(ErrorCode.USER_ERROR_A0210,"用户名或密码错误");
        }
    }

    @ApiOperation("用户注册")
    @PostMapping("/userRegisterByPhone")
    @ResponseBody
    BaseResponse<String> registerByPhone(@RequestBody User user,HttpSession session) {
        //非空校验
        if (Objects.equals(user.getUsername(), ""))return ResultUtils.error(ErrorCode.USER_ERROR_0001,"用户名不能为空");
        if (Objects.equals(user.getUserPassword(), ""))return ResultUtils.error(ErrorCode.USER_ERROR_0001,"密码不能为空");
        if (Objects.equals(user.getPhone(), ""))return ResultUtils.error(ErrorCode.USER_ERROR_0001,"手机号不能为空");

        if (userService.getUserInfoByName(user.getUsername()) == null)
            if (userService.getUserInfoByPhone(user.getPhone()) == null) {
                String pwd = user.getUserPassword();
                String MD5pwd = new SimpleHash("md5", pwd, "salt", 3).toString();
                user.setUserPassword(MD5pwd);
                if (userService.save(user)) {
                    UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), pwd);
                    Subject subject = SecurityUtils.getSubject();
                    subject.login(token);

                    UserVO userVO = user.toVo();
                    session.setAttribute("user", userVO);
                    return ResultUtils.success("注册成功");
                }
                else return ResultUtils.error(ErrorCode.SYSTEM_ERROR_B0001,"系统繁忙，稍后再试");
            } else return ResultUtils.error(ErrorCode.USER_ERROR_A0114,"该手机号已注册用户");
        else return ResultUtils.error(ErrorCode.USER_ERROR_A0111,"用户名已存在");
    }

    @ApiOperation("/删除用户（username）")
    @ResponseBody
    @DeleteMapping("/userDelete")
    @RequiresRoles("1")
    public BaseResponse<UserVO> delete(String username) {
        User user = userService.getUserInfoByName(username);
        if (user != null) {
            if (userService.removeById(user.getId())) return ResultUtils.success(user.toVo());
            else return ResultUtils.error(ErrorCode.SYSTEM_ERROR_B0001);
        } else return ResultUtils.error(ErrorCode.USER_ERROR_A0201);
    }


}
