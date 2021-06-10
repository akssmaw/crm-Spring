package com.example.springboot.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
@Data
public class Order {
    private int oid;
     private  int cid;
    private String phone;
    private int uid;
    private String uname;
    private String stuname;
    private String zipurl;
    private double prepay;
    private double total;
    private String account;
    private String con;
    private String rescon;
    private  String time;
    private int state;
    private String ordercon;
    private int orderstate;
    private String orderproject;
}
