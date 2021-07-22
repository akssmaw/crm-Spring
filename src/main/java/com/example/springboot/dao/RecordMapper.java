package com.example.springboot.dao;

import com.example.springboot.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
//增加record 增加con  记录表
    public int insertRecord(@Param("cid")int cid,@Param("lastuname")String lastuname,@Param("lastuid")int lastuid,@Param("lasttime")String lasttime,@Param("lastcon")String lastcon);
//根据cid查询多个备注的时间线
    public List<Record> SelectRecordById(@Param("cid")int cid);
}
