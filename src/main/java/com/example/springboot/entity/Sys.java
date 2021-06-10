package com.example.springboot.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Sys  implements Serializable {
     private int sid;
    private int uid;
    private int scheduledtime;
    private int scheduledday;
}
