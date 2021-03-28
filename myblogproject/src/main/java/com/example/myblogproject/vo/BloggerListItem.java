package com.example.myblogproject.vo;

public class BloggerListItem {
    private Integer id;
    private String username;
    private String signature;
    private String headImage;

    public BloggerListItem() {
    }

    public BloggerListItem(Integer id, String username, String signature, String headImage) {
        this.id = id;
        this.username = username;
        this.signature = signature;
        this.headImage = headImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Override
    public String toString() {
        return "BloggerListItem{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", signature='" + signature + '\'' +
                ", headImage='" + headImage + '\'' +
                '}';
    }
}
