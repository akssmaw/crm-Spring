package com.example.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.services.CrmdataServices;
import com.example.springboot.services.GetIpServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "所有数据接口")

@RestController
@RequestMapping(value = "ApiJiMuYuCon")
public class ApiJiMuYuCon {
    @Autowired

    private CrmdataServices crmdataServices;

    @Autowired
    private GetIpServices getIpServices;
    @ApiOperation("基木鱼提交接口")
    @PostMapping("/Add")
    public Object ApiJiMuYuCon_Add(@RequestBody  String tableData, HttpServletRequest request) {
        JSONArray json = (JSONArray) JSONArray.parse(tableData);
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i <json.size() ; i++) {
            JSONObject jo = (JSONObject) json.get(i);
            System.out.println(jo.getString("phone"));
            System.out.println(jo.getString("firsttime"));
            System.out.println(jo.getString("con"));
            System.out.println(jo.getString("plankey"));
            System.out.println(jo.getString("source"));
            System.out.println(jo.getString("url"));
            if( crmdataServices.SelectCrmdataByPhone(jo.getString("phone"))>=1){


                map.put("message","已经存在");
            }else {
                /*添加成功的用户*/
                if(crmdataServices.ApiJiMuYuCon_Add(
                        jo.getString("phone"),
                        jo.getString("source"),
                        jo.getString("url"),
                        jo.getString("con"),
                        jo.getString("firsttime"),
                        getIpServices.IpServices(request),
                        jo.getString("plankey"))==1){
                    map.put("message","添加成功");
                }else {
                    map.put("message","添加失败");
                }
            }
        }


        return  map;

    }

}
