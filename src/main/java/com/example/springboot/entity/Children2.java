package com.example.springboot.entity;

import lombok.Data;

@Data
public class Children2 {
    public int value;
    public String text;

    public Children2(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
