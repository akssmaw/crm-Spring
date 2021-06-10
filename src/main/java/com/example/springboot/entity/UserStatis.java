package com.example.springboot.entity;


import lombok.Data;

@Data
public class UserStatis {

    private int uid;
    private String uname;
    private int group;
    private int authority;
    private double prepay;
    private  double total;
    private int count;
    private int getnext2;
    private int getuser2count;
    private int setusercount;
}
