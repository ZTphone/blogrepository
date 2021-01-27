package com.example.myblogproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer essayId;
    private String content;
    private Integer favors;

    public Comment() {
    }

    public Comment(Integer id, Integer userId, Integer essayId, String content, Integer favors) {
        this.id = id;
        this.userId = userId;
        this.essayId = essayId;
        this.content = content;
        this.favors = favors;
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

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFavors() {
        return favors;
    }

    public void setFavors(Integer favors) {
        this.favors = favors;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", essayId=" + essayId +
                ", content='" + content + '\'' +
                ", favors=" + favors +
                '}';
    }
}
