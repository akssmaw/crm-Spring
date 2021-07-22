package com.example.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SetuserMapper {

    //增加表 以后用来做统计
    public int  InsertSetUser(@Param("cid")int cid,@Param("uid")int uid,@Param("iid")int iid,@Param("settime")String settime);
//根据cid uid 修改表的状态 让他查询不到 表示已经提交
//    88 已经放弃了  888 已经创建订单了
    int UpdateSetUserByCidAndUid(@Param("cid")int cid,@Param("uid")int uid,@Param("state")int state);


    //放弃客户
}
