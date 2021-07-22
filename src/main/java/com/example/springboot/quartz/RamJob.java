package com.example.springboot.quartz;


import com.example.springboot.services.ItemsServices;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;


public class RamJob implements Job {
    @Autowired
    private ItemsServices itemsServices;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("我是定时器");
        System.out.println(itemsServices.ItemsAll());
    }
}
