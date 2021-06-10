package com.example.springboot.controller;

import com.example.springboot.services.RecordServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "所有数据接口")

@RestController
@RequestMapping(value = "record")
public class RecordCon {
    @Autowired
    private RecordServices recordServices;
    @ApiOperation("根据cid多查询record ")
    @PostMapping("/RecordAllById/{cid}")
    public Object RecordAllById(@PathVariable int cid) {

        return  recordServices.SelectRecordById(cid);

    }
}
