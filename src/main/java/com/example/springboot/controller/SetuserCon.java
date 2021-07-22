package com.example.springboot.controller;

import com.example.springboot.services.ItemsServices;
import com.example.springboot.services.SetuserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "用户接口")

@RestController
@RequestMapping(value = "setuser")
public class SetuserCon {

    @Autowired
    private SetuserServices setuserServices;

    @ApiOperation("满意度 增加表 以后用来做统计")
    @PostMapping("/InsertSetUser/{cid}/{uid}/{iid}/{settime}")
    public Object itemsAll(@PathVariable int cid ,@PathVariable int uid ,@PathVariable int iid ,@PathVariable String settime ) {


        return  setuserServices.InsertSetUser(cid,uid,iid,settime);

    }

}
