package com.example.springboot.services;

import com.example.springboot.entity.OrderAndUid;
import org.apache.ibatis.annotations.Param;

public interface OrderAndServices {

    OrderAndUid SelectOrderAndUid(@Param("uid")int uid, @Param("starttime")String starttime, @Param("endtime")String endtime );

}
