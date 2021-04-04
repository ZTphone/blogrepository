package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.mapper.UserMapper;
import com.example.myblogproject.service.EditPrivacyImformation;
import com.example.myblogproject.utils.Log;
import com.example.myblogproject.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EditPrivacyImformationImpl implements EditPrivacyImformation {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectById(id);
        user.setPwd("");
        return user;
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
        Log.log("modifyInformation",user.getPwd());
        if(user.getPwd()=="") {
            user.setPwdNull();
        }else{
                user.setPwd(MD5Util.getSecondMD5(user.getPwd()));
                Log.log("afterTow",user.getPwd());
            }
        return userMapper.update(user,new QueryWrapper<User>().eq("id",user.getId()))!=0;
    }

    @Override
    public Boolean modifyHeadImage() {
        return null;
    }

    @Override
    public Boolean changeHeadImage(Integer id, String imageName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user = new User();
        user.setImage(imageName);
        return userMapper.update(user,queryWrapper)!=0;
    }
}
