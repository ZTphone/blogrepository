package com.example.myblogproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblogproject.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
