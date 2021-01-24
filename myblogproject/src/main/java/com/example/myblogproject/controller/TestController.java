package com.example.myblogproject.controller;

import com.example.myblogproject.dao.UserMapper;
import com.example.myblogproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

//    @Value("${school.name}")
//    private String school;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        String s = "hello,springboot";
        return s;
    }

    @RequestMapping("/selectAllUsers")
    @ResponseBody
    public List<User> selectAllUsers(){
        return userMapper.selectList(null);
    }
}
