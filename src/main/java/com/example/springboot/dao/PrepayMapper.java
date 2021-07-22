package com.example.springboot.dao;

import com.example.springboot.entity.Prepay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PrepayMapper {

    //增肌付款记录
    int insertPrepay(@Param("oid")int oid,@Param("pcon")String pcon,@Param("account")String account,@Param("prepay")double prepay);

    //查询付款
   List<Prepay> selectPrepay(@Param("oid")int oid);

}
