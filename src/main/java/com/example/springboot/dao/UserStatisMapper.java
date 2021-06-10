package com.example.springboot.dao;

import com.example.springboot.entity.UserStatis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserStatisMapper {

    List<UserStatis> SelectByDefault(@Param("stattime")String stattime,@Param("endtime")String endtime  );


    List<UserStatis> SelectByPrepay(@Param("stattime")String stattime,@Param("endtime")String endtime  );

    List<UserStatis> SelectByGroup(@Param("stattime")String stattime,@Param("endtime")String endtime  );


}
