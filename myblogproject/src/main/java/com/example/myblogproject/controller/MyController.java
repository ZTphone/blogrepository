package com.example.myblogproject.controller;

import com.example.myblogproject.entity.EssayContent;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.service.*;
import com.example.myblogproject.vo.BloggerListItem;
import com.example.myblogproject.vo.ListItem;
import com.example.myblogproject.vo.Result;
import com.example.myblogproject.vo.ShowEssayPageContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

//@CrossOrigin(origins = "127.0.0.1:8080")
@CrossOrigin
@Controller
public class MyController {

    @Autowired
    private WriteArticleService writeArticleService;
    @Autowired
    private ListService listService;
    @Autowired
    private LoginAndRegistService loginAndRegistService;
    @Autowired
    private ShowEssayService showEssayService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FavorsAndCollectService favorsAndCollectService;
    @Autowired
    private EditPrivacyImformation editPrivacyImformation;
    @Autowired
    private HotEssaysService hotEssaysService;
    @Autowired
    private FollowService followService;



    @RequestMapping(value = "/publishEssay")
    @ResponseBody
    public Result<Object> publishEssay(Integer userId, String title, String content){
        Result<Object> rs = new Result<>();
        if(writeArticleService.publishEssay(userId,title,content)){
            rs.setState(true);
            rs.setMessage("成功发布！");
        }else{
            rs.setState(false);
            rs.setMessage("出错啦！发布失败！");
        }
        return rs;
    }

    @RequestMapping(value = "/saveDraft")
    @ResponseBody
    public Result<Object> saveDraft(Integer userId, String title, String content){
        Result<Object> rs = new Result<>();
        if(writeArticleService.saveDraft(userId,title,content)){
            rs.setState(true);
            rs.setMessage("保存成功！");
        }else{
            rs.setState(false);
            rs.setMessage("出错啦！保存失败！");
        }
        return rs;
    }

    @RequestMapping("/editDraft")
    @ResponseBody
    public Result<Object> editDraft(Integer essayId,String title,String content,Integer state){
        if(writeArticleService.editDraft(essayId,title,content,state)){
            return Result.getSuccessResult("操作成功！");
        }else {
            return Result.getFailResult("操作失败！");
        }
    }



    @RequestMapping("/listLastestEssays")
    @ResponseBody
    public List<ListItem> listLastestEssays(){
        return listService.listLastest();
    }

    @RequestMapping("/listRecommendEssays")
    @ResponseBody
    public List<ListItem> listRecommendEssays(){
        return listService.listRecommend();
    }

    @RequestMapping("/listHotestEssays")
    @ResponseBody
    public List<ListItem> listHotestEssays(){
        return hotEssaysService.listHotest();
    }

    @RequestMapping("/listDraftEssays")
    @ResponseBody
    public List<ListItem> listDraftEssays(Integer userId){
        return listService.listDraft(userId);
    }

    @RequestMapping("/listFavorEssays")
    @ResponseBody
    public List<ListItem> listFavorEssays(Integer userId,HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession();

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
    public Result<Object> logintest(String username, String password, HttpServletRequest httpServletRequest){
        Result<Object> result = new Result<>();
        Integer userId = loginAndRegistService.checkUsernameAndPassword(username,password);
        if(userId!=0){
            result.setValue(userId);
            result.setMessage("登录成功！");
            result.setState(true);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("uid",userId);
        }else {
            result.setMessage("账号或密码错误！");
            result.setState(false);
        }
        return result;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Result<Object> register(User user){
        if(loginAndRegistService.register(user)) return Result.getSuccessResult("注册成功,请登录！");
        else return Result.getFailResult("用户名已被占用！");
    }



    @RequestMapping("/showEssayPageById")
    @ResponseBody
    public ShowEssayPageContent showEssayPageById(Integer userId, Integer essayId){
        return showEssayService.getEssayPageContent(userId, essayId);
    }


    @RequestMapping("/showHotEssayPageById")
    @ResponseBody
    public ShowEssayPageContent showHotEssayPageById(Integer userId, Integer essayId){
        return hotEssaysService.getEssayPageContent(userId, essayId);
    }

    @RequestMapping("/showEssayContentById")
    @ResponseBody
    public EssayContent getEssayContentById(Integer essayId){
        return showEssayService.showEssayContentById(essayId);
    }





    @RequestMapping("/addComment")
    @ResponseBody
    public Result<Object> addComment(Integer userId,Integer essayId,String content){
        if(commentService.addComment(userId,essayId,content)){
            return Result.getSuccessResult("发表成功！");
        }else {
            return Result.getFailResult("发表失败！");
        }
    }


    @RequestMapping("/checkCollected")
    @ResponseBody
    public Boolean checkCollected(Integer userId,Integer essayId){
        return favorsAndCollectService.hasCollect(userId,essayId);
    }

    @RequestMapping("/checkFavor")
    @ResponseBody
    public Boolean checkFavor(Integer userId,Integer essayId){
        return favorsAndCollectService.hasFavor(userId,essayId);
    }

    @RequestMapping("/addCollect")
    @ResponseBody
    public Boolean addCollect(Integer userId,Integer essayId){
        return favorsAndCollectService.addCollect(userId,essayId);
    }

    @RequestMapping("/addFavor")
    @ResponseBody
    public Boolean addFavor(Integer userId,Integer essayId){
        return favorsAndCollectService.addFavor(userId,essayId);
    }

    @RequestMapping("/cancelCollect")
    @ResponseBody
    public Boolean cancelCollect(Integer userId,Integer essayId){
        return favorsAndCollectService.cancelCollect(userId,essayId);
    }

    @RequestMapping("/cancelFavor")
    @ResponseBody
    public Boolean cancelFavor(Integer userId,Integer essayId){
        return favorsAndCollectService.cancelFavor(userId,essayId);
    }


    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Integer userId){
        return editPrivacyImformation.getUserById(userId);
    }

    @RequestMapping("/modifyInformation")
    @ResponseBody
    public Result<Object> modifyInformation(User user){
       if(editPrivacyImformation.modifyByUser(user)) return Result.getSuccessResult("修改成功");
       else return Result.getFailResult("修改失败");
    }

    @RequestMapping("/follow")
    @ResponseBody
    public Result<Object> follow(int userId,int bloggerId){
        if( followService.follow(userId,bloggerId)) return Result.getSuccessResult("关注成功！");
        else return Result.getFailResult("关注失败！");
    }

    @RequestMapping("/follow_list")
    @ResponseBody
    public List<BloggerListItem> followList(int userId){
       return followService.followList(userId);
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


}
