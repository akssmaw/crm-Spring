package com.example.springboot.controller;

import com.example.springboot.dao.notify.NotifyMapper;
import com.example.springboot.services.NotifyServices;
import com.example.springboot.services.SysServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "月份")

@RestController
@RequestMapping(value = "notify")
public class NotifyCon {
    @Autowired
    private NotifyServices notifyServices;
    @Autowired
    private SysServices sysServices;
    @ApiOperation("查询弹框")
    @PostMapping("/v1/{uid}")
    public Object notify1(
            @PathVariable("uid")int uid) throws ParseException {

       /*获取时间*/
        LocalDateTime dateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Map<String, Integer> map = new HashMap<>();
        int count_1=0;
        int count_2=0;
        int count_3=0;
        System.out.println(1);
        map.put("count_1",count_1);
        map.put("count_2",count_2);
        map.put("count_3",count_3);
        System.out.println(map);
/*判断数据上是不是没有*/


            for (int i = 0; i <notifyServices.SelectCrmdateByStat_0(uid).size() ; i++) {

                System.out.println("第一个for"+map);

                Date date1 = sdf.parse(notifyServices.SelectCrmdateByStat_0(uid).get(i).getLasttime());
                Date date2 = sdf.parse(dateTime.format(formatter));

                long time1 = date1.getTime();
                long time2 = date2.getTime();


                if(time2-time1>=(long)sysServices.SelectSysByscheduledtime().getScheduledday()*86400000){

                    count_1++;
                    System.out.println();
                    map.put("count_1",count_1);



                }




            }



                for (int i = 0; i < notifyServices.SelectCrmdateByStat_fu1(uid).size(); i++) {
                    //如果查看到时间是空 那么就是没有跟进线索 查询领取时间 和 现在时间对比
                    System.out.println("进入第二for"+notifyServices.SelectCrmdateByStat_fu1(uid).size());
                    map.put("count_1",count_1);
                    map.put("count_2",count_2);
                    map.put("count_3",count_3);
                    if(notifyServices.SelectCrmdateByStat_fu1(uid).get(i).getTime()==null){

                        Date date1 = sdf.parse(notifyServices.SelectCrmdateByStat_fu1(uid).get(i).getLasttime());
                        Date date2 = sdf.parse(dateTime.format(formatter));

                        long time1 = date1.getTime();
                        long time2 = date2.getTime();

                        if(time2-time1>=(long)sysServices.SelectSysByscheduledtime().getScheduledday()*86400000){

                            //判断是不是已经开单过的用户
                            if(sysServices.SelectCrmdataByscheduledOpenDan(notifyServices.SelectCrmdateByStat_fu1(uid).get(i).getCid())==0){

                                System.out.println(notifyServices.SelectCrmdateByStat_fu1(uid).get(i).getCid());
                               count_2++;
                                map.put("count_2",count_2);

                            }



                        }


                    }else {
                        System.out.println("进入不null");
                        Date date1 = sdf.parse(notifyServices.SelectCrmdateByStat_fu1(uid).get(i).getTime());
                        Date date2 = sdf.parse(dateTime.format(formatter));
                        long time1 = date1.getTime();
                        long time2 = date2.getTime();
                        if(time2-time1>(long)sysServices.SelectSysByscheduledtime().getScheduledday()*86400000 ){
                            System.out.println("有线索切超时");
                            if(sysServices.SelectCrmdataByscheduledOpenDan(
                                    notifyServices.SelectCrmdateByStat_fu1(uid).get(i).getCid())==0){
                                count_3++;
                                map.put("count_3",count_3);


                            }


                        }







                }




        }

        return map;
    }
}
