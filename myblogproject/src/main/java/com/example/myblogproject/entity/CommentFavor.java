package com.example.myblogproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class CommentFavor {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer commentId;

    public CommentFavor() {
    }

    public CommentFavor(Integer id, Integer userId, Integer commentId) {
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommentFavor{" +
                "id=" + id +
                ", userId=" + userId +
                ", commentId=" + commentId +
                '}';
    }
}
