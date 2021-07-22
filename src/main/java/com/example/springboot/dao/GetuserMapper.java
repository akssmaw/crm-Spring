package com.example.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GetuserMapper {

    //根据uid和时间 查询今天领取的客户和可领取的客户差
    public int UidAndTimeByGetnext(@Param("uid")int uid, @Param("gettime")String gettime);

    //增加一个Getuser
    public int insertGetuser(@Param("cid")int cid,@Param("uid")int uid, @Param("gettime")String gettime);


}
