package com.mymemo.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mymemo.demo.model.Bill;
import com.mymemo.demo.model.User;

import java.util.List;

public interface BillService extends IService<Bill> {
    List<Bill> getAllBills();
}
