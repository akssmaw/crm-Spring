package com.example.springboot.controller;

import com.example.springboot.entity.Items;
import com.example.springboot.entity.User;
import com.example.springboot.services.ItemsServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "用户接口")

@RestController
@RequestMapping(value = "items")
public class ItemsCon {



    @Autowired
    private ItemsServices itemsServices;

    @ApiOperation("获取公共导航")
    @PostMapping("/All")
    public Object itemsAll() {


        return  itemsServices.ItemsAll();

    }
}
