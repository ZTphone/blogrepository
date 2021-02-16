package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.mapper.UserMapper;
import com.example.myblogproject.service.EditPrivacyImformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EditPrivacyImformationImpl implements EditPrivacyImformation {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Boolean modifyPassworod(Integer id,String newPassword) {
        User user = new User();
        user.setId(id);
        user.setPwd(newPassword);
        return userMapper.updateById(user)!=0;
    }

    @Override
    public Boolean modifySex(Integer id,String sex) {
        User user = new User();
        user.setId(id);
        user.setSex(sex);
        return userMapper.updateById(user)!=0;
    }

    @Override
    public Boolean modifySignature(Integer id,String newSignature) {
        User user = new User();
        user.setId(id);
        user.setSignature(newSignature);
        return userMapper.updateById(user)!=0;
    }

    @Override
    public Boolean modifyByUser(User user) {
        return userMapper.update(user,new QueryWrapper<User>().eq("id",user.getId()))!=0;
    }

    @Override
    public Boolean modifyHeadImage() {
        return null;
    }
}
