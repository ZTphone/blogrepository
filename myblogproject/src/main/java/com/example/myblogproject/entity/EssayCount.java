package com.example.myblogproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class EssayCount implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer essayId;
    private Integer collectCount;
    private Integer favorCount;
    private Integer commentCount;
    private Integer score;

    public EssayCount() {
    }

    public EssayCount(Integer id, Integer essayId, Integer collectCount, Integer favorCount, Integer commentCount, Integer score) {
        this.id = id;
        this.essayId = essayId;
        this.collectCount = collectCount;
        this.favorCount = favorCount;
        this.commentCount = commentCount;
        this.score = score;
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

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "EssayCount{" +
                "id=" + id +
                ", essayId=" + essayId +
                ", collectCount=" + collectCount +
                ", favorCount=" + favorCount +
                ", commentCount=" + commentCount +
                ", score=" + score +
                '}';
    }
}
