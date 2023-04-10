package com.mymemo.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import com.mymemo.demo.model.vo.UserVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 用户昵称
     */
    @TableField("username")
    private String username;

    /**
     * 账户
     */
    @TableField("user_account")
    private String userAccount;

    /**
     * 头像地址
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 密码
     */
    @TableField("user_password")
    private String userPassword;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 0 是正常
     */
    @TableField("user_status")
    private Integer userStatus;

    /**
     * 0 -- 普通用户 1 -- 管理员
     */
    @TableField("user_role")
    private Integer userRole;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 2L;

    public UserVO toVo(){
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(this,userVO);
        return userVO;
    }
}
