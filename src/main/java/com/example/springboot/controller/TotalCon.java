package com.example.springboot.controller;

import com.example.springboot.entity.Total;
import com.example.springboot.services.TotalServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "用户接口")

@RestController
@RequestMapping(value = "total")
public class TotalCon {

    @Autowired
    private TotalServices totalServices;

    @ApiOperation("满意度 增加表 以后用来做统计")
    @PostMapping("/TotalAll/{starttime}/{endtime}")
    public Object TotalAll(@PathVariable("starttime")String starttime,@PathVariable("endtime")String endtime) {

        System.out.println(starttime);
        System.out.println(endtime);
        return totalServices.TotalAll(starttime, endtime);

    }
}