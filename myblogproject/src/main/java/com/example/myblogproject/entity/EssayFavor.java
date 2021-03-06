package com.example.myblogproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class EssayFavor implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer essayId;

    public EssayFavor() {
    }

    public EssayFavor(Integer id, Integer userId, Integer essayId) {
        this.id = id;
        this.userId = userId;
        this.essayId = essayId;
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

    @Override
    public String toString() {
        return "EssayFavor{" +
                "id=" + id +
                ", userId=" + userId +
                ", essayId=" + essayId +
                '}';
    }
}
