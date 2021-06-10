package com.example.springboot.controller;

import com.example.springboot.services.SatisfactionServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "所有数据接口")

@RestController
@RequestMapping(value = "satisfaction")
public class SatisfactionCon {
    @Autowired
    private SatisfactionServices satisfactionServices;
    @ApiOperation("查询满意度")
    @PostMapping("/satisfactionall")
    public Object SatisfactionAll() {

        return  satisfactionServices.SatisfactionAll();

    }

    @ApiOperation("查询销售的统计 根据两个time 和一个uid")
    @PostMapping("/SatisfactionByUidAndTime/{time1}/{time2}/{uid}")
    public Object SatisfactionByUidAndTime(@PathVariable String time1,@PathVariable String time2,@PathVariable int uid) {

        return  satisfactionServices.SatisfactionByUidAndTime(time1, time2, uid);

    }
}
