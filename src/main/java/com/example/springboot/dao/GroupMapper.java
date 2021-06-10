package com.example.springboot.dao;

import com.example.springboot.entity.Make;
import com.example.springboot.entity.MakeMoney;
import com.example.springboot.entity.MakeRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface GroupMapper {
    List<Object> SelectAllGroup();

    List<Make> SelectAllMakeCount(@Param("time1")String time1,@Param("time2")String time2,@Param("group")int group);

    MakeRate selectMakeRate(@Param("time1")String time1,@Param("time2")String time2,@Param("group")int group);

   MakeMoney selectMakeMoney(@Param("time1")String time1,@Param("time2")String time2 ,@Param("group")int group);
}
