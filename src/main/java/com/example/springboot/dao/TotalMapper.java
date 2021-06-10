package com.example.springboot.dao;

import com.example.springboot.entity.Total;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TotalMapper {

    List<Total> TotalAll(@Param("starttime")String starttime,@Param("endtime")String endtime);


}
