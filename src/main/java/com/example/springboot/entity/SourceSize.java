package com.example.springboot.entity;

import lombok.Data;

@Data
public class SourceSize {
    private String sourcename;
    private int allSize;
    private int effectiveSize;
    private int invalidSize;
}
