package com.mymemo.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mymemo.demo.model.Bill;
import org.springframework.stereotype.Repository;

@Repository
public interface BillMapper extends BaseMapper<Bill> {
}
