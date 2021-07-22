package com.example.springboot.controller;


import com.example.springboot.dao.CrmdataMapper;
import com.example.springboot.services.CrmdataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "SourceSize")
public class SourceSizeCon {

    @Autowired
    private CrmdataServices crmdataServices;

    @PostMapping("/SelectCrmdataBySourceSize/{starttime}/{endtime}")
    public Object SelectCrmdataBySourceSize(  @PathVariable("starttime")String starttime,
                                              @PathVariable("endtime")String endtime) {

        String [] s = {"百度","搜狗","360","小红书","豆瓣","快手"};
        Map<String, Object> map = new HashMap<>();
        List<Object> list  =new ArrayList<>();

        for (int i = 0; i < s.length; i++) {
            String nowsource = "'"+"%"+s[i].charAt(0)+"%"+s[i].charAt(s[i].length()-1)+"%"+"'";


            list.add(crmdataServices.SelectCrmdataBySourceSize(nowsource,starttime,endtime));
        }
        map.put("data",list);
        return  map;

    }
}
