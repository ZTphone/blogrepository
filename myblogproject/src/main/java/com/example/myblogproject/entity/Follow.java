package com.example.myblogproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Follow implements Serializable {

    @TableId(type = IdType.AUTO)
     private Integer id;
     private Integer userId;
     private Integer blogger;

    public Follow() {
    }

    public Follow(Integer id, Integer userId, Integer blogger) {
        this.id = id;
        this.userId = userId;
        this.blogger = blogger;
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

    public Integer getBlogger() {
        return blogger;
    }

    public void setBlogger(Integer blogger) {
        this.blogger = blogger;
    }

    @Override
    public String toString() {
        return "follow{" +
                "id=" + id +
                ", userId=" + userId +
                ", blogger=" + blogger +
                '}';
    }
}
