package com.example.myblogproject.vo;

import com.example.myblogproject.entity.Comment;
import com.example.myblogproject.entity.User;

public class CommentAndUser {
    private User user;
    private Comment comment;

    public CommentAndUser() {
    }

    public CommentAndUser(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentAndUser{" +
                "user=" + user +
                ", comment=" + comment +
                '}';
    }
}
