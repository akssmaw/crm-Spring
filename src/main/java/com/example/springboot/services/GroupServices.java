package com.example.springboot.services;

import com.example.springboot.entity.Make;
import com.example.springboot.entity.MakeMoney;
import com.example.springboot.entity.MakeRate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupServices {
    List<Object> SelectAllGroup();

    List<Make> SelectAllMakeCount(@Param("time1")String time1,@Param("time2")String time2,@Param("group")int group);

    MakeRate selectMakeRate(@Param("time1")String time1,@Param("time2")String time2,@Param("group")int group);

    MakeMoney selectMakeMoney(@Param("time1")String time1, @Param("time2")String time2 , @Param("group")int group);
}
