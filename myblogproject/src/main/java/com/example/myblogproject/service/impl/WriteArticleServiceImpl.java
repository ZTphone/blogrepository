package com.example.myblogproject.service.impl;

import com.example.myblogproject.mapper.EssayContentMapper;
import com.example.myblogproject.mapper.EssayMapper;
import com.example.myblogproject.entity.Essay;
import com.example.myblogproject.entity.EssayContent;
import com.example.myblogproject.service.WriteArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WriteArticleServiceImpl implements WriteArticleService {

    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private EssayContentMapper essayContentMapper;


    @Override
    public Boolean saveDraft(Integer userId, String title, String content) {
        return this.addEssay(userId,title,content,1);
    }

    @Override
    public Boolean publishEssay(Integer userId, String title, String content) {
        return this.addEssay(userId,title,content,2);
    }

    @Transactional
    @Override
    public Boolean addEssay(Integer userId, String title, String content,Integer state) {
        Essay newEssay = new Essay();
        newEssay.setTitle(title);
        newEssay.setAuthor(userId);
        newEssay.setCreateTime(new Date());
        newEssay.setModifyTime(new Date());
        newEssay.setState(state);
        essayMapper.insert(newEssay);
        Integer newEssayId = essayMapper.queryIdByEssay(newEssay);

        EssayContent essayContent = new EssayContent();
        essayContent.setEssayId(newEssayId);
        essayContent.setContent(content);

        return essayContentMapper.insert(essayContent)!=0;
    }
}
