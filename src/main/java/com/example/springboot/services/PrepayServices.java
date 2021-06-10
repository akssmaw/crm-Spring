package com.example.springboot.services;

import com.example.springboot.entity.Prepay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrepayServices {

    int insertPrepay(@Param("oid")int oid,@Param("pcon")String pcon,@Param("account")String account,@Param("prepay")double prepay);

    List<Prepay> selectPrepay(@Param("oid")int oid);

}
