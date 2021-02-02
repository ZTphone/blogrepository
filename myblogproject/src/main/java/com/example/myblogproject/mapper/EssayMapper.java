package com.example.myblogproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblogproject.entity.Essay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EssayMapper extends BaseMapper<Essay> {
    Integer queryIdByEssay(@Param(value = "essay") Essay essay);
}
