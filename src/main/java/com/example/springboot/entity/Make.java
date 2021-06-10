package com.example.springboot.entity;


import lombok.Data;

@Data
public class Make {
   private int uid;
   private String uname;
   private int group;
   private int overallcount;
   private  int validcount;
}
