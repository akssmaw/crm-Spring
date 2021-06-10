package com.example.springboot.dao;

import com.example.springboot.entity.Month;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MonthMapper {

    Month SelectMonth(@Param("starTime")String starTime, @Param("endtime")String endtime );
}
