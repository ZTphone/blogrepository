package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.EssayCount;
import com.example.myblogproject.mapper.EssayCountMapper;
import com.example.myblogproject.service.EssayCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EssayCountServiceImpl implements EssayCountService {
    @Autowired
    private EssayCountMapper essayCountMapper;

    @Override
    public boolean addCollectScore(Integer essayId) {

        EssayCount essayCount = essayCountMapper.selectOne(new QueryWrapper<EssayCount>().eq("essay_id",essayId));
        essayCount.setCollectCount(essayCount.getCollectCount()+1);
        essayCount.setScore(essayCount.getScore()+COLLECT_WEIGHT);
        essayCountMapper.updateById(essayCount);
        return true;
    }

    @Override
    public boolean addFavorScore(Integer essayId) {
        EssayCount essayCount = essayCountMapper.selectOne(new QueryWrapper<EssayCount>().eq("essay_id",essayId));
        essayCount.setFavorCount(essayCount.getFavorCount()+1);
        essayCount.setScore(essayCount.getScore()+FAVOR_WEIGHT);
        essayCountMapper.updateById(essayCount);
        return true;
    }

    @Override
    public boolean addCommentScore(Integer essayId) {
        EssayCount essayCount = essayCountMapper.selectOne(new QueryWrapper<EssayCount>().eq("essay_id",essayId));
        essayCount.setCommentCount(essayCount.getCommentCount()+1);
        essayCount.setScore(essayCount.getScore()+COMMENT_WEIGHT);
        essayCountMapper.updateById(essayCount);
        return true;
    }

    @Override
    public boolean cancelCollectScore(Integer essayId) {
        EssayCount essayCount = essayCountMapper.selectOne(new QueryWrapper<EssayCount>().eq("essay_id",essayId));
        essayCount.setCollectCount(essayCount.getCollectCount() - 1);
        essayCount.setScore(essayCount.getScore() - COLLECT_WEIGHT);
        essayCountMapper.updateById(essayCount);
        return true;
    }

    @Override
    public boolean cancelFavorScore(Integer essayId) {
        EssayCount essayCount = essayCountMapper.selectOne(new QueryWrapper<EssayCount>().eq("essay_id",essayId));
        essayCount.setFavorCount(essayCount.getFavorCount()-1);
        essayCount.setScore(essayCount.getScore()-FAVOR_WEIGHT);
        essayCountMapper.updateById(essayCount);
        return true;
    }

    @Override
    public boolean cancelCommentScore(Integer essayId) {
        EssayCount essayCount = essayCountMapper.selectOne(new QueryWrapper<EssayCount>().eq("essay_id",essayId));
        essayCount.setCommentCount(essayCount.getCommentCount()-1);
        essayCount.setScore(essayCount.getScore()-COMMENT_WEIGHT);
        essayCountMapper.updateById(essayCount);
        return true;
    }
}
