package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.Collect;
import com.example.myblogproject.entity.EssayFavor;
import com.example.myblogproject.mapper.CollectMapper;
import com.example.myblogproject.mapper.EssayFavorMapper;
import com.example.myblogproject.service.FavorsAndCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavorsAndCollectServiceImpl implements FavorsAndCollectService {
    @Autowired
    private EssayFavorMapper essayFavorMapper;
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Boolean addFavor(Integer userId, Integer essayId) {
        if(this.hasFavor(userId,essayId)) return true;
        EssayFavor essayFavor = new EssayFavor();
        essayFavor.setUserId(userId);
        essayFavor.setEssayId(essayId);
        int r =essayFavorMapper.insert(essayFavor);
        return r!=0;
    }

    @Override
    public Boolean addCollect(Integer userId, Integer essayId) {
        if(this.hasCollect(userId,essayId)) return true;
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setEssayId(essayId);
        int r =collectMapper.insert(collect);
        return r!=0;
    }

    @Override
    public Integer getEssayFavorCountById(Integer essayId) {
        QueryWrapper<EssayFavor> q1 = new QueryWrapper<>();
        q1.eq("essay_id",essayId);
        return essayFavorMapper.selectCount(q1);
    }

    @Override
    public Integer getEssayCollectCountById(Integer essayId) {
        QueryWrapper<Collect> q1 = new QueryWrapper<>();
        q1.eq("essay_id",essayId);
        Integer collectCount = collectMapper.selectCount(q1);
        return collectCount;
    }

    @Override
    public Boolean hasFavor(Integer userId, Integer essayId) {
        QueryWrapper<EssayFavor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("essay_id",essayId);
        int r =essayFavorMapper.selectCount(queryWrapper);
        return r!=0;
    }

    @Override
    public Boolean hasCollect(Integer userId, Integer essayId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("essay_id",essayId);
        return collectMapper.selectCount(queryWrapper)!=0;
    }

    @Override
    public Boolean cancelFavor(Integer userId, Integer essayId) {
        if(!this.hasFavor(userId,essayId)) return true;
        QueryWrapper<EssayFavor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("essay_id",essayId);
        return essayFavorMapper.delete(queryWrapper)!=0;
    }

    @Override
    public Boolean cancelCollect(Integer userId, Integer essayId) {
        if(!this.hasCollect(userId,essayId)) return true;
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("essay_id",essayId);
        return collectMapper.delete(queryWrapper)!=0;
    }
}
