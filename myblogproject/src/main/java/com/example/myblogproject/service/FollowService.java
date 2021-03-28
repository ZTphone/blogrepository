package com.example.myblogproject.service;

import com.example.myblogproject.entity.User;
import com.example.myblogproject.vo.BloggerListItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowService {
    Boolean follow(Integer userId,Integer bloggerId);
    Boolean unfollow(Integer userId,Integer bloggerId);
    Integer followCount(Integer bloggerId);
    List<BloggerListItem> followList(Integer userId);
}
