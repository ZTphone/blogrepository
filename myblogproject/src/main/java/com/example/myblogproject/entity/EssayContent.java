package com.example.myblogproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class EssayContent {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer essayId;
    private String content;

    public EssayContent() {
    }

    public EssayContent(Integer id, Integer essayId, String content) {
        this.id = id;
        this.essayId = essayId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "EssayContent{" +
                "id=" + id +
                ", essayId=" + essayId +
                ", content='" + content + '\'' +
                '}';
    }
}
