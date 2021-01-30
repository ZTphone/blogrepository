package com.example.myblogproject.service;

import org.springframework.stereotype.Service;

@Service
public interface WriteArticleService {
    public Boolean saveDraft(Integer userId,String title,String content);
    public Boolean publishEssay(Integer userId,String title,String content);
    public Boolean addEssay(Integer userId,String title,String content,Integer state);
}
