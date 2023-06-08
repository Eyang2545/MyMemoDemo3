package com.mymemo.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value = "useraction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAction implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名称
     */
    @TableField("username")
    private String username;

    /**
     * 用户行为
     */
    @TableField("user_action")
    private String userAction;

    /**
     * 时间
     */
    @TableField("time")
    private LocalDateTime time;

    /**
     * ip地址
     */
    @TableField("ip")
    private String ip;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 2L;
}
