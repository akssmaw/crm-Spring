package com.example.springboot.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Sys  implements Serializable {
     private int sid;
    private int uid;
    private int scheduledtime;/*开启或者关闭*/
    private int scheduledday;/*时间*/
}
