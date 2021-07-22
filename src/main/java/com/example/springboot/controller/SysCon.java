package com.example.springboot.controller;


import com.example.springboot.entity.Sys;
import com.example.springboot.services.SysServices;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "定时接口")

@RestController
@RequestMapping(value = "SysCon")
public class SysCon {
    @Autowired
    private SysServices sysServices;

    @PostMapping("/SelectSys")
    public  Object SelectSysByscheduledtime( ){


        return sysServices.SelectSysByscheduledtime();
    }
    @PostMapping("/UpdateSysByScheduledtime/{scheduledtime}/{scheduledday}")
    public  Object UpdateSysByScheduledtime(@PathVariable("scheduledtime")int scheduledtime,
                                            @PathVariable("scheduledday")int scheduledday){


        return sysServices.UpdateSysByScheduledtime(scheduledtime, scheduledday) ;
    }
}
