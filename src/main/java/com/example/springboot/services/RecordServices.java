package com.example.springboot.services;

import com.example.springboot.entity.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordServices {

    public int insertRecord(@Param("cid")int cid, @Param("lastuname")String lastuname, @Param("lastuid")int lastuid, @Param("lasttime")String lasttime, @Param("lastcon")String lastcon);

    //根据cid查询多个备注的时间线
    public List<Record> SelectRecordById(@Param("cid")int cid);
}
