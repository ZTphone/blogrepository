package com.example.myblogproject.vo;

import com.example.myblogproject.entity.Comment;
import com.example.myblogproject.entity.Essay;
import com.example.myblogproject.entity.EssayContent;
import com.example.myblogproject.entity.User;

import java.util.List;

public class ShowEssayPageContent {
    private User author;
    private Essay essay;
    private EssayContent essayContent;
    private Integer favorCount;
    private Integer collectCount;
    private List<CommentAndUser> commentList;

    public ShowEssayPageContent() {
    }

    public ShowEssayPageContent(User author, Essay essay, EssayContent essayContent, Integer favorCount, Integer collectCount, List<CommentAndUser> commentList) {
        this.author = author;
        this.essay = essay;
        this.essayContent = essayContent;
        this.favorCount = favorCount;
        this.collectCount = collectCount;
        this.commentList = commentList;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Essay getEssay() {
        return essay;
    }

    public void setEssay(Essay essay) {
        this.essay = essay;
    }

    public EssayContent getEssayContent() {
        return essayContent;
    }

    public void setEssayContent(EssayContent essayContent) {
        this.essayContent = essayContent;
    }

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public List<CommentAndUser> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentAndUser> commentList) {
        this.commentList = commentList;
    }
}
