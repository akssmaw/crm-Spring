package com.example.springboot;

import com.example.springboot.quartz.quartz;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication


public class SpringbootApplication {

    @Autowired
    public quartz quartz;

    public static void main(String[] args) throws ParseException, SchedulerException {
      /*  com.example.springboot.quartz.quartz.quart();*/
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
