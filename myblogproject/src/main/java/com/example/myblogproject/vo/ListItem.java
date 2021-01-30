package com.example.myblogproject.vo;

import com.example.myblogproject.entity.Essay;

public class ListItem {
    private Integer essayId;
    private String title;
    private String headImage;
    private String author;
    private Integer collectCount;
    private Integer favorCount;
    private Integer commentCOunt;

    public ListItem() {
    }

    public ListItem(Integer essayId, String title, String headImage, String author, Integer collectCount, Integer favorCount, Integer commentCOunt) {
        this.essayId = essayId;
        this.title = title;
        this.headImage = headImage;
        this.author = author;
        this.collectCount = collectCount;
        this.favorCount = favorCount;
        this.commentCOunt = commentCOunt;
    }

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Integer getCommentCOunt() {
        return commentCOunt;
    }

    public void setCommentCOunt(Integer commentCOunt) {
        this.commentCOunt = commentCOunt;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "essayId=" + essayId +
                ", title='" + title + '\'' +
                ", headImage='" + headImage + '\'' +
                ", author='" + author + '\'' +
                ", collectCount=" + collectCount +
                ", favorCount=" + favorCount +
                ", commentCOunt=" + commentCOunt +
                '}';
    }
}
