package com.example.springboot.dao.chat;

import com.example.springboot.entity.chat.chat_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatUserMapper {

    List<chat_user> Chat_UserByComId(@Param("cid")int cid);

    chat_user chatUserById(@Param("id")int id);

}
