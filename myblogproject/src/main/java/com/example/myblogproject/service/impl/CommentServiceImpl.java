package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.Comment;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.mapper.CommentMapper;
import com.example.myblogproject.mapper.UserMapper;
import com.example.myblogproject.service.CommentService;
import com.example.myblogproject.service.EssayCountService;
import com.example.myblogproject.vo.CommentAndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EssayCountService essayCountService;



    @Override

    public Boolean addComment(Integer userId, Integer essayId, String content) {
        Comment comment  = new Comment();
        comment.setUserId(userId);
        comment.setEssayId(essayId);
        comment.setContent(content);
        boolean flag = commentMapper.insert(comment)!=0;
        essayCountService.addCommentScore(essayId);
        return flag;
    }

    @Override
    public Boolean deleteComment(Integer commentId) {
        return commentMapper.deleteById(commentId)!=0;
    }

    @Override
    public Boolean addFavor(Integer userId, Integer commentId) {
        Comment comment = commentMapper.selectById(commentId);
        comment.setFavors(comment.getFavors()+1);
        return commentMapper.updateById(comment)!=0;
    }

    @Override
    public Boolean cancelFavor(Integer userId, Integer commentId) {
        Comment comment = commentMapper.selectById(commentId);
        comment.setFavors(comment.getFavors()-1);
        return commentMapper.updateById(comment)!=0;
    }

    @Override
    public List<CommentAndUser> getCommentListByEssayId(Integer essayId) {
        List<CommentAndUser> commentAndUserList = new ArrayList<>();
        List<Comment> commentList = commentMapper.selectList(new QueryWrapper<Comment>().eq("essay_id",essayId));
        for(Comment comment: commentList){
            User user = userMapper.selectById(comment.getUserId());
            CommentAndUser commentAndUser = new CommentAndUser();
            commentAndUser.setUser(user);
            commentAndUser.setComment(comment);
            commentAndUserList.add(commentAndUser);
        }
        return commentAndUserList;
    }

    @Override
    public Integer getCommentCountByEssayId(Integer essayId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("essay_id",essayId);
        return commentMapper.selectCount(queryWrapper);
    }
}
