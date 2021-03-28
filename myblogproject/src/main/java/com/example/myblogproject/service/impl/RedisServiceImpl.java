package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myblogproject.entity.*;
import com.example.myblogproject.mapper.EssayContentMapper;
import com.example.myblogproject.mapper.EssayCountMapper;
import com.example.myblogproject.mapper.EssayMapper;
import com.example.myblogproject.mapper.UserMapper;
import com.example.myblogproject.service.*;
import com.example.myblogproject.utils.RedisUtil;
import com.example.myblogproject.vo.ListItem;
import com.example.myblogproject.vo.ShowEssayPageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisSevice {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private EssayContentMapper essayContentMapper;
    @Autowired
    private EssayCountMapper essayCountMapper;

    @Autowired
    private ListService listService;

    @Autowired
    private ShowEssayService showEssayService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private FavorsAndCollectService favorsAndCollectService;

    @Override
    public  Boolean initHotData() {
        int hotNum = 10;
        QueryWrapper<EssayCount> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("score");
        IPage<EssayCount> page = new Page<>(1,hotNum);
        page = essayCountMapper.selectPage(page,queryWrapper);
        List<EssayCount> essayCountList = page.getRecords();

        List<Integer> essayIdList = new ArrayList<>();
        for(EssayCount ec : essayCountList){
            essayIdList.add(ec.getEssayId());
        }
        QueryWrapper<Essay> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("id",essayIdList);

        List<ListItem> itemList = new ArrayList<>();
        for(Integer essayId : essayIdList){
            itemList.add(listService.getListItembyEssayId(essayId));
        }
        RedisUtil.set("itemList",itemList);

        // 保存每个热门文章页数据到redis
        String pre = "EssayPage:";
        ShowEssayPageContent showEssayPageContent = null;
        Integer userId = 0;
        for(Integer id : essayIdList){
           showEssayPageContent = showEssayService.getEssayPageContent(userId,id);
           RedisUtil.set(pre+id.toString(),showEssayPageContent);
        }

        return true;

    }


    @Override
    public Boolean updateData() {
        RedisUtil.flush();
        this.initHotData();
        return true;
    }

    @Override
    public ShowEssayPageContent getEssayPageById(Integer essayId) {
        String key = "EssayPage:"+essayId.toString();
        System.out.println("key??"+key);
        ShowEssayPageContent showEssayPageContent = RedisUtil.get(key,ShowEssayPageContent.class);
        System.out.println(showEssayPageContent);
        return showEssayPageContent;
    }

    @Override
    public List<ListItem> listHotest() {

        return RedisUtil.get("itemList",List.class);
    }
}
