package com.example.springboot.entity;


import lombok.Data;

@Data
public class MakeMoney {

    private int group;
    private double prepaysum;
    private double totalsum;
    private int count;
}
