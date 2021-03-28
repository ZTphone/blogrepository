package com.example.myblogproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Follow implements Serializable {

    @TableId(type = IdType.AUTO)
     private Integer id;
     private Integer userId;
     private Integer bloggerId;

    public Follow() {
    }

    public Follow(Integer id, Integer userId, Integer bloggerId) {
        this.id = id;
        this.userId = userId;
        this.bloggerId = bloggerId;
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

    public Integer getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Integer bloggerId) {
        this.bloggerId = bloggerId;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", userId=" + userId +
                ", bloggerId=" + bloggerId +
                '}';
    }
}
