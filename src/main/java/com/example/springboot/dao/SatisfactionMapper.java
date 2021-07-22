package com.example.springboot.dao;


import com.example.springboot.entity.Satisfaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SatisfactionMapper {

//查询全部由的满意度
    public List<Satisfaction> SatisfactionAll();

    //查询销售的统计 根据两个time 和一个uid
    public List<Satisfaction> SatisfactionByUidAndTime(@Param("time1")String time1,@Param("time2")String time2,@Param("uid")int uid);
}
