package com.example.myblogproject.service.impl;

import com.example.myblogproject.service.HotEssaysService;
import com.example.myblogproject.service.RedisSevice;
import com.example.myblogproject.utils.RedisUtil;
import com.example.myblogproject.vo.ListItem;
import com.example.myblogproject.vo.ShowEssayPageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotEssaysServiceImpl implements HotEssaysService {

    @Autowired
    private RedisSevice redisSevice;

    @Override
    public List<ListItem> listHotest() {
        System.out.println(RedisUtil.isEmpty()+"Empty????");
        if(RedisUtil.get("itemList")==null) redisSevice.initHotData();
        List<ListItem> list = redisSevice.listHotest();
        System.out.println(list);
        return list;
    }

    @Override
    public ShowEssayPageContent getEssayPageContent(Integer userId, Integer essayId) {
        return redisSevice.getEssayPageById(essayId);
    }

}
