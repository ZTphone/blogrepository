package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.Follow;
import com.example.myblogproject.mapper.FollowMapper;
import com.example.myblogproject.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public Boolean follow(Integer userId, Integer bloggerId) {
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setBlogger(bloggerId);
        return followMapper.insert(follow)!=0;
    }

    @Override
    public Boolean unfollow(Integer userId, Integer bloggerId) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("blogger_id",bloggerId);
        return followMapper.delete(queryWrapper)!=0;
    }

    @Override
    public Integer followCount(Integer bloggerId) {
        return followMapper.selectCount(new QueryWrapper<Follow>().eq("blogger_id",bloggerId));
    }
}
