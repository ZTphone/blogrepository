package com.example.myblogproject.controller;

import com.example.myblogproject.mapper.*;
import com.example.myblogproject.mapper.EssayFavorMapper;
import com.example.myblogproject.entity.*;
import com.example.myblogproject.service.EditPrivacyImformation;
import com.example.myblogproject.service.WriteArticleService;
import com.example.myblogproject.utils.RedisUtil;
import com.example.myblogproject.vo.Result;
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

    @RequestMapping(value = "/update_image",method = RequestMethod.POST)
    public void uploadAvatar(@RequestParam(value = "file") MultipartFile file,
                                       HttpServletRequest request) throws IllegalStateException, IOException {
        //1.确定保存的文件夹
        //String dirPath = "/head";
        String dirPath = request.getSession().getServletContext().getRealPath("/static/images/");
        System.out.println("dirPath="+dirPath);
        System.out.println(request.getParameter("userId"));

//        File dir = new File(dirPath);
//        if(!dir.exists()) {
//            dir.mkdir();
//        }
        //2.确定保存的文件名
        String idString = request.getParameter("userId");
        String orginalFilename = file.getOriginalFilename();
        int beginIndex = orginalFilename.lastIndexOf(".");
        String suffix ="";

        if(beginIndex!=-1) {
            suffix = orginalFilename.substring(beginIndex);
        }
        String filename = idString+suffix;
        //创建文件对象，表示要保存的头像文件,第一个参数表示存储的文件夹，第二个参数表示存储的文件
        //File dest = new File(dir,filename);
        //执行保存
        System.out.println("file****************"+dirPath+filename);
        file.transferTo(new File(dirPath+filename));
        //更新数据表
        //String avatar = "/upload/"+filename;
        // 通过uid找到用户

        editPrivacyImformation.changeHeadImage(Integer.parseInt(idString),filename);
        //return Result.getSuccessResult("上传头像成功");
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

}
