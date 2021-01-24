package com.example.myblogproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblogproject.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
