package com.example.springboot.dao;


import com.example.springboot.entity.OrderAndUid;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderAndUidMapper {

    OrderAndUid  SelectOrderAndUid(@Param("uid")int uid,@Param("starttime")String starttime,@Param("endtime")String endtime );

}
