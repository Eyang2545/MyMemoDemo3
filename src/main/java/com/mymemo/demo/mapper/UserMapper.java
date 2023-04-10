package com.mymemo.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mymemo.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
