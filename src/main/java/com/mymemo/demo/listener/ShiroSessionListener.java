package com.mymemo.demo.listener;

import com.mymemo.demo.model.UserAction;
import com.mymemo.demo.model.vo.UserVO;
import com.mymemo.demo.service.UserActionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class ShiroSessionListener implements SessionListener {

    private UserActionService userActionService;


    @Override
    public void onStart(Session session) {

    }


    @Override
    public void onStop(Session session) {
//        try {
//            System.out.println("--------------");
//            UserVO userVO = (UserVO) session.getAttribute("user");
//            UserAction userAction = new UserAction();
//            userAction.setUsername(userVO.getUsername());
//            userAction.setUserAction("登出");
//            userAction.setTime(LocalDateTime.now());
//            userAction.setIp(session.getHost());
//            System.out.println(userAction);
//            userActionService.save(userAction);
//        } catch (Exception e) {
//            System.out.println("Failed to save user action: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

    @Override
    public void onExpiration(Session session) {

    }

    public void setUserActionService(UserActionService userActionService) {
        this.userActionService = userActionService;
    }
}

