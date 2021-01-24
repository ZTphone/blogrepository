package com.example.myblogproject.entity;

import lombok.Data;


public class User {
    private Integer id;
    private String username;
    private  String pwd;
    private String sex;
    private String signature;
    private String image;

    public User() {
    }

    public User(Integer id, String username, String pwd, String sex, String signature, String image) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.sex = sex;
        this.signature = signature;
        this.image = image;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", signature='" + signature + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
