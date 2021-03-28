package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.entity.Follow;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.mapper.FollowMapper;
import com.example.myblogproject.mapper.UserMapper;
import com.example.myblogproject.service.FollowService;
import com.example.myblogproject.vo.BloggerListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean follow(Integer userId, Integer bloggerId) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("blogger_id",bloggerId);
        if(followMapper.selectCount(queryWrapper)!=0) return true;
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setBloggerId(bloggerId);
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

    @Override
    public List<BloggerListItem> followList(Integer userId) {
        List<BloggerListItem> list = new ArrayList<>();
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Follow> followList = followMapper.selectList(queryWrapper);
        for(Follow f:followList){
            User user = userMapper.selectById(f.getBloggerId());
            BloggerListItem bloggerListItem = new BloggerListItem();
            bloggerListItem.setId(user.getId());
            bloggerListItem.setHeadImage(user.getImage());
            bloggerListItem.setSignature(user.getSignature());
            bloggerListItem.setUsername(user.getUsername());
            list.add(bloggerListItem);
        }
        return list;
    }
}
