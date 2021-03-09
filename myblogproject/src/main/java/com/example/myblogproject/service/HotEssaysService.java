package com.example.myblogproject.service;

import com.example.myblogproject.vo.ListItem;
import com.example.myblogproject.vo.ShowEssayPageContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotEssaysService {
    List<ListItem> listHotest();
    ShowEssayPageContent getEssayPageContent(Integer userId,Integer essayId);
}
