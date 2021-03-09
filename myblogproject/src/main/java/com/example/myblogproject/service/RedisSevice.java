package com.example.myblogproject.service;

import com.example.myblogproject.entity.Comment;
import com.example.myblogproject.entity.Essay;
import com.example.myblogproject.entity.EssayContent;
import com.example.myblogproject.entity.EssayCount;
import com.example.myblogproject.vo.ListItem;
import com.example.myblogproject.vo.ShowEssayPageContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RedisSevice {
    Boolean initHotData();
    Boolean updateData();
    ShowEssayPageContent getEssayPageById(Integer essayId);
    List<ListItem> listHotest();

}
