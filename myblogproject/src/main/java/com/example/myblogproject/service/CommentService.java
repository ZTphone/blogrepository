package com.example.myblogproject.service;

import com.example.myblogproject.vo.CommentAndUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Boolean addComment(Integer userId,Integer essayId,String content);
    Boolean deleteComment(Integer commentId);
    Boolean addFavor(Integer userId, Integer commentId);
    Boolean cancelFavor(Integer userId, Integer commentId);
    List<CommentAndUser> getCommentListByEssayId(Integer essayId);
    Integer getCommentCountByEssayId(Integer essayId);
}
