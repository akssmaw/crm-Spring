package com.example.springboot.entity.chat;

import lombok.Data;

@Data
public class chat_user {

int id;
int cId;
String userName;
String userPwd;
String userNikeName;
String userNumber;
String userQQ;
String userWx;
int userState; //用户状态  0禁止登陆 1可以登陆
    int userLogoState;  //用户在线状态  0不在线 1在线
    int userWeight;//用户的权重
}
