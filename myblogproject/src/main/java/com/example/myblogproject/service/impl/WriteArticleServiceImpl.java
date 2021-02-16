package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public Integer queryIdByEssay(Essay essay) {
        QueryWrapper<Essay> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("state",essay.getState());
        queryWrapper.eq("author",essay.getAuthor());
        queryWrapper.eq("title",essay.getTitle());
        Essay tempEssay = essayMapper.selectOne(queryWrapper);
        return tempEssay.getId();
    }

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

        // 获取新增的essay的id
        Integer newEssayId = this.queryIdByEssay(newEssay);

        EssayContent essayContent = new EssayContent();
        essayContent.setEssayId(newEssayId);
        essayContent.setContent(content);

        return essayContentMapper.insert(essayContent)!=0;
    }

    @Override
    public Boolean editDraft(Integer essayId, String title, String content, Integer state) {
        //更新标题
        Essay essay = new Essay();
        essay.setId(essayId);
        essay.setTitle(title);
        essay.setState(state);
        essayMapper.updateById(essay);

        //更新文章内容
        EssayContent essayContent = new EssayContent();
        essayContent.setContent(content);
        QueryWrapper<EssayContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("essay_id",essayId);
        essayContentMapper.update(essayContent,queryWrapper);
        return true;
    }
}
