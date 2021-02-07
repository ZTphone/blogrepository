package com.example.myblogproject.controller;

import com.example.myblogproject.service.ListService;
import com.example.myblogproject.service.LoginAndRegistService;
import com.example.myblogproject.vo.ListItem;
import com.example.myblogproject.vo.LoginForm;
import com.example.myblogproject.vo.Result;
import com.example.myblogproject.service.WriteArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600)
@CrossOrigin
@Controller
public class MyController {

    @Autowired
    private WriteArticleService writeArticleService;
    @Autowired
    private ListService listService;
    @Autowired
    private LoginAndRegistService loginAndRegistService;



    @RequestMapping(value = "/publishEssay")
    @ResponseBody
    public Result<Object> publishEssay(Integer userId, String title, String content){
        Result<Object> rs = new Result<>();
        if(writeArticleService.publishEssay(userId,title,content)){
            rs.setState(true);
            rs.setMessage("成功发布！");
        }else{
            rs.setState(false);
            rs.setMessage("出错啦！");
        }
        return rs;
    }

    @RequestMapping(value = "/saveDraft")
    @ResponseBody
    public Result<Object> saveDraft(Integer userId, String title, String content){
        Result<Object> rs = new Result<>();
        if(writeArticleService.saveDraft(userId,title,content)){
            rs.setState(true);
            rs.setMessage("成功发布！");
        }else{
            rs.setState(false);
            rs.setMessage("出错啦！");
        }
        return rs;
    }

    @RequestMapping("/listLastestEssays")
    @ResponseBody
    public List<ListItem> listLastestEssays(){
        return listService.listLastest();
    }

    @RequestMapping("/listHotestEssays")
    @ResponseBody
    public List<ListItem> listHotestEssays(){
        return listService.listHotest();
    }

    @RequestMapping("/listDraftEssays")
    @ResponseBody
    public List<ListItem> listDraftEssays(){
        return listService.listDraft(1);
    }

    @RequestMapping("/listFavorEssays")
    @ResponseBody
    public List<ListItem> listFavorEssays(Integer userId){
        return listService.listFavor(userId);
    }

    @RequestMapping("/listCommentedEssays")
    @ResponseBody
    public List<ListItem> listCommentedEssays(Integer userId){
        return listService.listCommented(userId);
    }

    @RequestMapping("/listCollectedEssays")
    @ResponseBody
    public List<ListItem> listCollectedEssays(Integer userId){
        return listService.listCollected(userId);
    }

    @RequestMapping("/listPublishedEssays")
    @ResponseBody
    public List<ListItem> listPublishedEssays(Integer userId){
        return listService.listPublished(userId);
    }




    @RequestMapping("/logintest")
    @ResponseBody
    public Result<Object> logintest(String username,String password){
        System.out.println("..............");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        Result<Object> result = new Result<>();
        if(loginAndRegistService.checkUsernameAndPassword(username,password)){
            result.setMessage("登录成功！");
            result.setState(true);
        }else {
            result.setMessage("账号或密码错误！");
            result.setState(false);
        }
        return result;
    }


}
