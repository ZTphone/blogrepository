package com.example.myblogproject.service;

import com.example.myblogproject.vo.ListItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListService {

    List<ListItem> listLastest();
    List<ListItem> listHotest();
    List<ListItem> listRecommend();
    List<ListItem> listPublished(Integer userId);
    List<ListItem> listDraft(Integer userId);
    List<ListItem> listFavor(Integer userId);
    List<ListItem> listCollected(Integer userId);
    List<ListItem> listCommented(Integer userId);

}
