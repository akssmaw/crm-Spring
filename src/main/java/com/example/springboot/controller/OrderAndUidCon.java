package com.example.springboot.controller;


import com.example.springboot.services.OrderAndServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "OrderAnd")
public class OrderAndUidCon {

    @Autowired
    private OrderAndServices orderAndServices;

    @PostMapping("/SelectOrderAndUid/{uid}/{starttime}/{endtime}")
    public Object SelectOrderAndUid(@PathVariable("uid")int uid,@PathVariable("starttime")String starttime,@PathVariable("endtime")String endtime) {

        return orderAndServices.SelectOrderAndUid(uid, starttime, endtime);

    }
}
