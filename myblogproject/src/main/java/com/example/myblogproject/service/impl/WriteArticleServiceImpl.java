package com.example.myblogproject.service.impl;

import com.example.myblogproject.dao.EssayMapper;
import com.example.myblogproject.entity.Essay;
import com.example.myblogproject.service.WriteArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WriteArticleServiceImpl implements WriteArticleService {

    @Autowired
    private EssayMapper essayMapper;


    @Override
    public Boolean saveDraft(Integer userId, String title, String content) {
        return this.addEssay(userId,title,content,1);
    }

    @Override
    public Boolean publishEssay(Integer userId, String title, String content) {
        return this.addEssay(userId,title,content,2);
    }

    @Override
    public Boolean addEssay(Integer userId, String title, String content,Integer state) {
        Essay newEssay = new Essay();
        newEssay.setTitle(title);
        newEssay.setContent(content);
        newEssay.setAuthor(userId);
        newEssay.setCreateTime(new Date());
        newEssay.setModifyTime(new Date());
        newEssay.setState(state);
        return essayMapper.insert(newEssay)!=0;
    }
}
