package com.example.myblogproject.controller;

import com.example.myblogproject.dao.*;
import com.example.myblogproject.dao.EssayFavorMapper;
import com.example.myblogproject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class TestController {

//    @Value("${school.name}")
//    private String school;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private CommentFavorMapper commentFavorMapper;
    @Autowired
    private EssayFavorMapper  essayFavorMapper;


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

    @RequestMapping("/selectAllEssays")
    @ResponseBody
    public List<Essay> selectAllEssays(){
        List<Essay> list = essayMapper.selectList(null);
        for(Essay e : list){
            Date date = e.getCreateTime();
            System.out.println("............."+date);
        }
        return list;
    }

    @RequestMapping("/insertEssay")
    @ResponseBody
    public Integer insertEssay(){
        Essay essay = new Essay();
        essay.setTitle("双十一购物节");
        essay.setContent("在中国，每年的双十一，都是人们购物狂欢的日子");
        essay.setState(0);
        essay.setAuthor(1);
        System.out.println(new Date());
        essay.setCreateTime(new Date());
        essay.setModifyTime(new Date());
        Integer rs = essayMapper.insert(essay);
        return rs;
    }

    @RequestMapping("/selectAllComment")
    @ResponseBody
    public List<Comment> selectAllComment(){
        return commentMapper.selectList(null);
    }

    @RequestMapping("/selectAllCollect")
    @ResponseBody
    public List<Collect> selectAllCollect(){
        return collectMapper.selectList(null);
    }

    @RequestMapping("/selectAllCommentFavor")
    @ResponseBody
    public List<CommentFavor> selectAllCommentFavor(){
        return commentFavorMapper.selectList(null);
    }

    @RequestMapping("/selectAllEssayFavor")
    @ResponseBody
    public List<EssayFavor> selectAllEssayFavor(){
        return essayFavorMapper.selectList(null);
    }

    @RequestMapping("/testEdit")
    @ResponseBody
    public ModelAndView testEdit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit.html");
        return modelAndView;
    }



}
