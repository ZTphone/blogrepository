package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.mapper.UserMapper;
import com.example.myblogproject.service.LoginAndRegistService;
import com.example.myblogproject.utils.Log;
import com.example.myblogproject.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginAndRegistServiceImpl implements LoginAndRegistService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer checkUsernameAndPassword(String username, String password) {
        Log.log("afterFisrt",password);
        Log.log("afterSecond",MD5Util.getSecondMD5(password));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("pwd", MD5Util.getSecondMD5(password));
        List<User> list = userMapper.selectList(queryWrapper);

        if(list.size()==0) return 0;
        else {
            return list.get(0).getId();
        }
    }

    @Override
    public Boolean register(User user) {
        String pwdMd5 = MD5Util.getSecondMD5(user.getPwd());
        user.setPwd(pwdMd5);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        List<User> list = userMapper.selectList(queryWrapper);
        if(list.size()!=0) return false;
        userMapper.insert(user);
        return true;
    }
}
