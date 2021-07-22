package com.example.springboot.services;

import org.apache.ibatis.annotations.Param;

public interface SetuserServices {

    public int  InsertSetUser(@Param("cid")int cid, @Param("uid")int uid, @Param("iid")int iid, @Param("settime")String settime);

    //根据cid uid 修改表的状态 让他查询不到 表示已经提交
    int UpdateSetUserByCidAndUid(@Param("cid")int cid,@Param("uid")int uid,@Param("state")int state);

}
