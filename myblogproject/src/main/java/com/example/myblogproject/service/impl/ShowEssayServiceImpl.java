package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.Essay;
import com.example.myblogproject.entity.EssayContent;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.mapper.EssayContentMapper;
import com.example.myblogproject.mapper.EssayMapper;
import com.example.myblogproject.mapper.UserMapper;
import com.example.myblogproject.service.CommentService;
import com.example.myblogproject.service.FavorsAndCollectService;
import com.example.myblogproject.service.ShowEssayService;
import com.example.myblogproject.vo.CommentAndUser;
import com.example.myblogproject.vo.ShowEssayPageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowEssayServiceImpl implements ShowEssayService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private EssayContentMapper essayContentMapper;

    @Autowired
    private CommentService commentService;
    @Autowired
    private FavorsAndCollectService favorsAndCollectService;



    @Override
    public User showAuthorById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Essay showEssayById(Integer id) {
        return essayMapper.selectById(id);
    }

    @Override
    public EssayContent showEssayContentById(Integer id) {
        return essayContentMapper.selectById(id);
    }

    @Override
    public ShowEssayPageContent getEssayPageContent(Integer essayId) {

        Essay essay = this.showEssayById(essayId);

        User author = this.showAuthorById(essay.getAuthor());

        EssayContent essayContent = essayContentMapper.selectOne(new QueryWrapper<EssayContent>().eq("essay_id",essayId));

        Integer favorCount = favorsAndCollectService.getEssayFavorCountById(essayId);

        Integer collectCount = favorsAndCollectService.getEssayCollectCountById(essayId);

        List<CommentAndUser> commentList = commentService.getCommentListByEssayId(essayId);

        ShowEssayPageContent showEssayPageContent = new ShowEssayPageContent();
        showEssayPageContent.setAuthor(author);
        showEssayPageContent.setEssay(essay);
        showEssayPageContent.setEssayContent(essayContent);
        showEssayPageContent.setCollectCount(collectCount);
        showEssayPageContent.setFavorCount(favorCount);
        showEssayPageContent.setCommentList(commentList);
        return showEssayPageContent;
    }
}
