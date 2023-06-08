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

@TableName(value = "bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private long billId;

    /**
     * 用户昵称
     */
    @TableField("create_datetime")
    private LocalDateTime createDatetime;

    @TableField("account_id")
    private String accountId;

    @TableField("customer_id")
    private String customerId;

    @TableField("total_amount")
    private String totalAmount;

    @TableField("amount_paid")
    private String amountPaid;

    @TableField("amount_due")
    private String amountDue;

    @TableField("payment_status")
    private String paymentStatus;

    @TableField("payment_due_date")
    private LocalDateTime paymentDueDate;

    @TableField("billing_address")
    private String billingAddress;

    @TableField("creator")
    private String creator;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 2L;
}
