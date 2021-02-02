package com.example.myblogproject.service;

import org.springframework.stereotype.Service;

@Service
public interface FollowService {
    Boolean follow(Integer userId,Integer bloggerId);
    Boolean unfollow(Integer userId,Integer bloggerId);
    Integer followCount(Integer bloggerId);
}
