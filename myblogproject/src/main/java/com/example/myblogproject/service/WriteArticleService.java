package com.example.myblogproject.service;

import com.example.myblogproject.entity.Essay;
import org.springframework.stereotype.Service;

@Service
public interface WriteArticleService {
    public Boolean saveDraft(Integer userId,String title,String content);
    public Boolean publishEssay(Integer userId,String title,String content);
    public Boolean editDraft(Integer essayId,String title,String content,Integer state);
    public Boolean addEssay(Integer userId,String title,String content,Integer state);
    Integer queryIdByEssay(Essay essay);
}
