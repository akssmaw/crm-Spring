package com.example.springboot.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("Crmdata-用户实体类")
@Data
public class Crmdata implements Serializable {
    private int cid;

    private String phone;

    private String source;

    private String url;

    private String con;

    private String datasource;

    private String firsttime;

    private String lastuname;

    private int lastuid;

    private String lasttime;

    private int stat;

    private String ip;

    private String plankey;

    private int color;
}
