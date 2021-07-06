package com.example.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.services.CrmdataServices;
import com.example.springboot.services.GetIpServices;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "UploadExcelCon")
public class UploadExcelCon {

    @Autowired
    private CrmdataServices crmdataServices;

    @Autowired
    private GetIpServices getIpServices;

    @PostMapping("/UploadJson")
    public Object UploadJson(@RequestBody  String tableData, HttpServletRequest request) {

//                    map.put("data_1",list);/*已经存在的用户*/
//                    map.put("data_2",list2 ); /*添加成功的用户*/
//                    map.put("data_3",list3 );/*添加失败的用户*/
        JSONArray json = (JSONArray) JSONArray.parse(tableData);
        Map<Object, Object> map = new HashMap<>();
        List<String> list =new ArrayList<>();
        List<String> list2 =new ArrayList<>();
        List<String> list3 =new ArrayList<>();
        for (int i = 0; i <json.size() ; i++) {
            JSONObject jo = (JSONObject)json.get(i);
            System.out.println(jo.getString("phone"));
            System.out.println(jo.getString("firsttime"));
            System.out.println(jo.getString("con"));
            System.out.println(jo.getString("plankey"));
            System.out.println(jo.getString("source"));
            System.out.println(jo.getString("url"));
            System.out.println("-------------------------");
            if( crmdataServices.SelectCrmdataByPhone(jo.getString("phone"))>=1){
//                /*已经存在的用户*/
                list.add(jo.getString("phone"));
                map.put("data_1",list);
                map.put("data_2",list2 );
                map.put("data_3",list3 );
            }else {
                /*添加成功的用户*/
                if(crmdataServices.InsertCrmdata4(
                        jo.getString("phone"),
                        jo.getString("source"),
                        jo.getString("url"),
                        jo.getString("con"),
                        jo.getString("firsttime"),
                        getIpServices.IpServices(request),
                        jo.getString("plankey"))==1){
                    list2.add(jo.getString("phone"));
                    map.put("data_1",list);/*已经存在的用户*/
                    map.put("data_2",list2 ); /*添加成功的用户*/
                    map.put("data_3",list3 );/*添加失败的用户*/
                }else {
                    /*添加失败的用户*/
                    list3.add(jo.getString("phone"));
                    map.put("data_1",list);
                    map.put("data_2",list2 );
                    map.put("data_3",list3 );
                }
            }

        }

        return   map;

    }

}
