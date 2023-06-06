package com.mymemo.demo.controller;

import com.mymemo.demo.common.BaseResponse;
import com.mymemo.demo.common.ResultUtils;
import com.mymemo.demo.model.vo.UserVO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "页面管理模块")
@Controller
@RequestMapping("/index")
public class IndexController {
    @GetMapping("/login")
    public String login (){
        return "login.html";
    }
    @GetMapping("/system")
    public String loginFail (){
        return "system.html";
    }
    @GetMapping("/main")
    public String mainPage (){
        return "main.html";
    }
    @GetMapping("/register")
    public String register () {
        return "register.html";
    }

}
