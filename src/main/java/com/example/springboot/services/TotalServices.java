package com.example.springboot.services;

import com.example.springboot.entity.Total;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TotalServices {

    List<Total> TotalAll(@Param("starttime")String starttime,@Param("endtime")String endtime );


}
