package com.mymemo.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mymemo.demo.mapper.BillMapper;
import com.mymemo.demo.model.Bill;
import com.mymemo.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Bill> getAllBills() {
        return null;
    }
}
