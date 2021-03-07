package com.example.myblogproject.service;

import com.example.myblogproject.entity.Comment;
import com.example.myblogproject.entity.Essay;
import com.example.myblogproject.entity.EssayContent;
import com.example.myblogproject.entity.EssayCount;

public interface RedisSevice {
    Boolean initHotData();
    Essay getEssayByIdFromRedis();
    EssayContent getEssayContentByEssayIdFromRedis();
    EssayCount getEssayCountByEssayIdFromRedis();
    Comment getCommentByEssayIdFromRedis();

}
