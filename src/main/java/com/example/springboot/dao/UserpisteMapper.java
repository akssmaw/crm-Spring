package com.example.springboot.dao;

import com.example.springboot.entity.Userpiste;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserpisteMapper {

    //查询线索
      List<Userpiste> UserpisteAll(@Param("cid")int cid);

      int InsertUserpiste(@Param("cid")int cid,@Param("ptime")String ptime,@Param("pcon")String pcon,@Param("uid")int uid,@Param("uname")String uname);

      int DeleteUserpisteByPid(@Param("pid")int pid);

}
