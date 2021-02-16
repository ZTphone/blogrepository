package com.example.myblogproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblogproject.mapper.*;
import com.example.myblogproject.entity.*;
import com.example.myblogproject.service.CommentService;
import com.example.myblogproject.service.FavorsAndCollectService;
import com.example.myblogproject.service.ListService;
import com.example.myblogproject.vo.ListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListServiceImpl implements ListService {
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    private EssayFavorMapper essayFavorMapper;
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FavorsAndCollectService favorsAndCollectService;
    @Autowired
    private CommentService commentService;

    /**
     *
     * @return
     */


    @Override
    public List<ListItem> listLastest() {
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",2);
        queryWrapper.orderByDesc("modify_time");
        return this.getList(queryWrapper);
    }

    @Override
    public List<ListItem> listHotest() {
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",2);
        List<ListItem> list = this.getList(queryWrapper);
        Collections.sort(list, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return o2.getFavorCount()-o1.getFavorCount();
            }
        });
        return list;
    }

    @Override
    public List<ListItem> listRecommend() {
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",2);
        List<ListItem> list = this.getList(queryWrapper);
        Collections.sort(list, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return (o2.getFavorCount()+o2.getCollectCount()+o2.getCommentCount())-(o1.getFavorCount()+o1.getCollectCount()+o1.getCommentCount());
            }
        });
        return list;
    }

    @Override
    public List<ListItem> listPublished(Integer userId) {
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author",userId);
        queryWrapper.eq("state",2);
        return this.getList(queryWrapper);
    }

    @Override
    public List<ListItem> listDraft(Integer userId) {
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author",userId);
        queryWrapper.eq("state",1);
        List<ListItem> list = this.getList(queryWrapper);
        return list;
    }

    @Override
    public List<ListItem> listFavor(Integer userId) {
        Set<Integer> set = new HashSet<>();
        List<EssayFavor> favorList = essayFavorMapper.selectList(new QueryWrapper<EssayFavor>().eq("user_id",userId));
        for(EssayFavor e : favorList){
            set.add(e.getEssayId());
        }
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",set);
        return this.getList(queryWrapper);
    }

    @Override
    public List<ListItem> listCollected(Integer userId) {
        Set<Integer> set = new HashSet<>();
        List<Collect> collectList = collectMapper.selectList(new QueryWrapper<Collect>().eq("user_id",userId));
        for(Collect e : collectList){
            set.add(e.getEssayId());
        }
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",set);
        return this.getList(queryWrapper);
    }

    @Override
    public List<ListItem> listCommented(Integer userId) {
        Set<Integer> set = new HashSet<>();
        List<Comment> commentList = commentMapper.selectList(new QueryWrapper<Comment>().eq("user_id",userId));
        for(Comment e : commentList){
            set.add(e.getEssayId());
        }
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",set);
        return this.getList(queryWrapper);
    }

    public List<ListItem> getList(QueryWrapper<Essay> queryWrapper){
        List<ListItem> list  = new ArrayList<>();
        List<Essay> essayList = essayMapper.selectList(queryWrapper);
        for(Essay e :  essayList){
            ListItem listItem = new ListItem();
            listItem.setEssayId(e.getId());
            listItem.setTitle(e.getTitle());
            User author = userMapper.selectById(e.getAuthor());
            listItem.setAuthor(author.getUsername());
            listItem.setHeadImage(author.getImage());
            listItem.setCollectCount(favorsAndCollectService.getEssayCollectCountById(e.getId()));
            listItem.setCommentCount(commentService.getCommentCountByEssayId(e.getId()));
            listItem.setFavorCount(favorsAndCollectService.getEssayFavorCountById(e.getId()));
            list.add(listItem);
        }
        return list;
    }

}
