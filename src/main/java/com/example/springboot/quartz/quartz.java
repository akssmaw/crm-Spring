package com.example.springboot.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;


import java.text.ParseException;

@Service
public class quartz {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {




    }

    public static void  quart() throws SchedulerException, ParseException {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(RamJob.class)
                .withIdentity("job1", "group1").build();
        // 3、构建Trigger实例,每隔1s执行一次
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        triggerBuilder.withIdentity("tigger1", "group1");
        triggerBuilder.startNow();
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0 * * * * ? * " )));
        Trigger trigger = triggerBuilder.build();


        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();
    }

}
