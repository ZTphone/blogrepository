package com.example.myblogproject.service;

import org.springframework.stereotype.Service;

@Service
public interface EssayCountService {
    int COLLECT_WEIGHT = 3;
    int FAVOR_WEIGHT = 2;
    int COMMENT_WEIGHT = 1;

    boolean addCollectScore(Integer essayId);
    boolean addFavorScore(Integer essayId);
    boolean addCommentScore(Integer essayId);
    boolean cancelCollectScore(Integer essayId);
    boolean cancelFavorScore(Integer essayId);
    boolean cancelCommentScore(Integer essayId);
}
