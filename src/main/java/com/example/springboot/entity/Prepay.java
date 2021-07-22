package com.example.springboot.entity;

import lombok.Data;

@Data
public class Prepay {

   private int pid;
  private   int oid;
    private  String ptime;
    private String pcon;
    private   String account;
    private double prepay;

}
