package com.example.myblogproject.service;

import com.example.myblogproject.entity.Essay;
import com.example.myblogproject.entity.EssayContent;
import com.example.myblogproject.entity.User;
import com.example.myblogproject.vo.ShowEssayPageContent;
import org.springframework.stereotype.Service;

@Service
public interface ShowEssayService {
    User showAuthorById(Integer id);
    Essay showEssayById(Integer id);
    EssayContent showEssayContentById(Integer essayid);
    ShowEssayPageContent getEssayPageContent(Integer userId,Integer essayId);
}
