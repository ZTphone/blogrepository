package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.mapper.UserMapper;
import com.example.myblogproject.service.LoginAndRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginAndRegistServiceImpl implements LoginAndRegistService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean checkUsernameAndPassword(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("pwd",password);
        List<User> list = userMapper.selectList(queryWrapper);
        return list.size()!=0;
    }

    @Override
    public Boolean register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        List<User> list = userMapper.selectList(queryWrapper);
        if(list.size()!=0) return false;
        userMapper.insert(user);
        return true;
    }
}
