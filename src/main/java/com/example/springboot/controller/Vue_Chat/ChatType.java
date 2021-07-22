package com.example.springboot.controller.Vue_Chat;

public enum ChatType {
    Com_Is_Null("Com_Is_Null"),//公司不存在
    Com_Not_Null("Com_Not_Null"),//公司存在
    UserWeight_Is_Null("User_Is_Null"),//权限都是0 请联系管理员
    User_Id("User_Id");//权限都是0 请联系管理员
    String des; // 描述

    ChatType(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }
}