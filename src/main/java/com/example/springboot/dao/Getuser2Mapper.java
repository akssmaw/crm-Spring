package com.example.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Getuser2Mapper {
    //根据uid和时间 查询今天领取的客户和可领取的客户差
    public int UidAndTimeByGetnext2(@Param("uid")int uid, @Param("gettime")String gettime);

    //增加一个Getuser
    public int insertGetuser2(@Param("cid")int cid,@Param("uid")int uid, @Param("gettime")String gettime);

    public int selectGetuser2Count(@Param("cid")int cid);

}
