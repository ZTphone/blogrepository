package com.example.myblogproject.controller;

import com.example.myblogproject.mapper.*;
import com.example.myblogproject.mapper.EssayFavorMapper;
import com.example.myblogproject.entity.*;
import com.example.myblogproject.service.EditPrivacyImformation;
import com.example.myblogproject.service.HotEssaysService;
import com.example.myblogproject.service.WriteArticleService;
import com.example.myblogproject.utils.RedisUtil;
import com.example.myblogproject.vo.Result;
import com.example.myblogproject.vo.ShowEssayPageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
//@CrossOrigin(origins = "http://127.0.0.1:8080")
@CrossOrigin
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

    @Autowired
    private WriteArticleService writeArticleService;

    @Autowired
    private EditPrivacyImformation editPrivacyImformation;

    @Autowired
    private HotEssaysService hotEssaysService;

    private RedisUtil redisUtil;


//    @RequestMapping("/test")
//    @ResponseBody
//    public String test(){
//        String s = "hello,springboot";
//        return s;
//    }

    @RequestMapping("/queryIdByEssay")
    @ResponseBody
    public Integer queryIdByEssay(){

        Essay e = new Essay();
        e.setState(2);
        e.setAuthor(2);
        e.setTitle("怀念的过去");
        return writeArticleService.queryIdByEssay(e);
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
        //essay.setContent("在中国，每年的双十一，都是人们购物狂欢的日子");
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

    @RequestMapping("/testpost")
    @ResponseBody
    public String testpost(String tn){
      //  System.out.println("===="+tn);
        String rs = tn+"kk";
        return rs;
    }



    @RequestMapping("/testSetRedis")
    @ResponseBody
    public void testSetRedis(){
        EssayCount essayCount = new EssayCount();
        essayCount.setId(1);
        essayCount.setEssayId(2);
        essayCount.setCollectCount(3);
        essayCount.setFavorCount(4);
        essayCount.setCommentCount(5);
        essayCount.setScore(6);
        RedisUtil.set("ObjectOne",essayCount);
    }

    @RequestMapping("/testGetRedis")
    @ResponseBody
    public void testGetRedis(){
        System.out.println(RedisUtil.get("ObjectOne"));
    }

    @RequestMapping("/testGetEssayPage")
    @ResponseBody
    public ShowEssayPageContent testGetEssayPage(){

        Collect collect = new Collect(1,2,3);
        System.out.println("666");
//        log.info("c",collect);
        return hotEssaysService.getEssayPageContent(0,19);
    }

    @RequestMapping("/testSLF4J")
    @ResponseBody
    public void testSLF4J(){
        Collect collect = new Collect(1,2,3);
        System.out.println("666");
//        log.info("c",collect);
//        log.debug("k",collect);
    }


}
