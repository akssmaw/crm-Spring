package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.example.springboot.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "redis")
@RestController
public class RedisCon {
    @Autowired
    private RedisUtil redisUtil;
    @GetMapping("/getredis")
    public Object redis() {
        redisUtil.set("name","zhangsan2");

        return "6666".toString();

    }
}
