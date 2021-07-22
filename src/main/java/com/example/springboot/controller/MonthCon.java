package com.example.springboot.controller;

import com.example.springboot.dao.MonthMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "月份")

@RestController
@RequestMapping(value = "Month")
public class MonthCon {

    @Autowired
    private MonthMapper monthMapper;

    @ApiOperation("全部月份循环")
    @PostMapping("/MonthAll/{date}")
    public Object itemsAll(@PathVariable("date")String date) {
        String[ ] arr={"01","02","03","04","05","06","07","08","09","10","11","12"};
//        System.out.println(date+"-"+arr[0]+"-"+"01"+" "+"00:00:00");
//        System.out.println(date+"-"+arr[0]+"-"+"32"+" "+"00:00:00");
        List<Object> list = new ArrayList<>();
                for (int i = 0; i < arr.length; i++) {
//            System.out.println("开始"+date+"-"+arr[0]+"-"+"01"+" "+"00:00:00"+"---截至"+date+"-"+arr[0]+"-"+"32"+" "+"00:00:00");
                    System.out.println(date+"-"+arr[i]+"-"+"01"+" "+"00:00:00");
                    System.out.println(date+"-"+arr[i]+"-"+"32"+" "+"00:00:00");
                    list.add(monthMapper.SelectMonth(date+"-"+arr[i]+"-"+"01"+" "+"00:00:00",date+"-"+arr[i]+"-"+"32"+" "+"00:00:00").getMonthtotal());

                    System.out.println(monthMapper.SelectMonth(date+"-"+arr[i]+"-"+"01"+" "+"00:00:00",date+"-"+arr[i]+"-"+"32"+" "+"00:00:00"));
        };

//        System.out.println(66666);

        return list;


    }
}
