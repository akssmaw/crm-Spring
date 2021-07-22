package com.example.springboot.controller;

import com.example.springboot.dao.UserStatisMapper;
import com.example.springboot.services.UserStatisServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "用户接口")

@RestController
@RequestMapping(value = "UserStatis")
public class UserStatisCon {


    @Autowired
        private UserStatisServices userStatisServices;

    @ApiOperation("获取公共导航")
    @PostMapping("/All/{stattime}/{endtime}/{bySort}")
    public Object SelectBy(@PathVariable("stattime")String stattime,
                           @PathVariable("endtime")String endtime,
                           @PathVariable("bySort")int bySort
                           ) {

        if(bySort==0){
            System.out.println();
            return  userStatisServices.SelectByDefault(stattime, endtime);
        }else if(bySort==1){
            return  userStatisServices.SelectByPrepay(stattime, endtime);
        }else if(bySort==2){
            return  userStatisServices.SelectByGroup(stattime, endtime);
        }




        return  null;

    }
}
