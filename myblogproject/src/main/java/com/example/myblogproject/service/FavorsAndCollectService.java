package com.example.myblogproject.service;

import org.springframework.stereotype.Service;

@Service
public interface FavorsAndCollectService {
    Boolean addFavor(Integer userId,Integer essayId);
    Boolean addCollect(Integer userId,Integer essayId);
    Integer getEssayFavorCountById(Integer id);
    Integer getEssayCollectCountById(Integer id);
    Boolean hasFavor(Integer userId,Integer essayId);
    Boolean hasCollect(Integer userId,Integer essayId);
    Boolean cancelFavor(Integer userId,Integer essayId);
    Boolean cancelCollect(Integer userId,Integer essayId);
}
