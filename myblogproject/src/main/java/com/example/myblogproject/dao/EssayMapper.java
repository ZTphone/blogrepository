package com.example.myblogproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblogproject.entity.Essay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EssayMapper extends BaseMapper<Essay> {
}
