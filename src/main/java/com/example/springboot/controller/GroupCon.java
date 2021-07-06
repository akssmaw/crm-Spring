package com.example.springboot.controller;

import com.example.springboot.dao.GroupMapper;
import com.example.springboot.entity.MakeMoney;
import com.example.springboot.entity.MakeRate;
import com.example.springboot.services.GroupServices;
import com.example.springboot.services.ItemsServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "所有数据接口")

@RestController
@RequestMapping(value = "group")
public class GroupCon {

    @Autowired
    private GroupServices groupServices;

    @ApiOperation("全部的组")
    @PostMapping("/All/{time1}/{time2}")
    public Object All(@PathVariable("time1")String time1,@PathVariable("time2")String time2) {
Map<Object,Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        List<MakeRate> list2 = new ArrayList<>();
        List<MakeMoney> list3 = new ArrayList<>();
        for (int i = 1; i <groupServices.SelectAllGroup().size()+1 ; i++) {

         list.add(groupServices.SelectAllMakeCount(time1,time2,i));
            list2.add(groupServices.selectMakeRate(time1,time2,i));
            list3.add(groupServices.selectMakeMoney(time1,time2,i));
            System.out.println(groupServices.selectMakeMoney(time1,time2,i));
        }
/*小组不能为0 最少为1*/
        map.put("data",list);
        map.put("data2",list2);
        map.put("data3",list3);
        map.put("size",groupServices.SelectAllGroup().size());
        return map ;

    }
}
