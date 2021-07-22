package com.example.springboot.dao.chat;

import com.example.springboot.entity.chat.chat_com;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatComMapper {
//根据公司id查询
    chat_com Chat_ComByComId(@Param("comid")String comid);

    //查询有没有这个公司
    int Chat_ComByComIdCheck(@Param("comid")String comid);
}
