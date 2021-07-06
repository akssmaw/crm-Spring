package com.example.springboot.quartz;

import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataScheduled;
import com.example.springboot.services.RecordServices;
import com.example.springboot.services.SysServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@SuppressWarnings({"ALL", "AlibabaTransactionMustHaveRollback"})
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务

public class Time {

    @Autowired
    private SysServices sysServices;
    @Autowired
    private RecordServices recordServices;
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Scheduled(cron = "0 0 0 * * ?")
    /*
     *    @Scheduled(cron = "0/15 * * * * ?")
	@Scheduled(cron = "0 0 0 * * ?")凌晨0点
	* Scheduled：0 0 1 * * ?
     * */
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    @Transactional(rollbackFor = Exception.class)

    public void configureTasks() {

        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//        System.out.println(itemsServices.ItemsAll());

        if (sysServices.SelectSysByscheduledtime().getScheduledtime() == 1) {
            /*获取时间*/
            LocalDateTime dateTime = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateTime.format(formatter));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            /*获取时间*/
            Map<String, Object> map =new HashMap();


            try {
            List<Crmdata> list =new ArrayList<>();
                System.out.println("getScheduledtime==1 执行");
                System.out.println("执行");

                for (int i = 0; i <sysServices.SelectCrmdataByscheduledtime().size() ; i++) {

                    Date date1 = sdf.parse(sysServices.SelectCrmdataByscheduledtime().get(i).getLasttime());
                    Date date2 = sdf.parse(dateTime.format(formatter));

                    long time1 = date1.getTime();
                    long time2 = date2.getTime();


                    if(time2-time1>=(long)sysServices.SelectSysByscheduledtime().getScheduledday()*86400000){
                        /*自动流出*/


                                /*日志*/
                        list.add(sysServices.SelectCrmdataByscheduledtime().get(i));

                    }else {

                        System.out.println("还没有超时");

                        System.out.println("cid"+sysServices.SelectCrmdataByscheduledtime().get(i).getCid()+"数据库"+time1+"-----"+"现在时间"+time2+"-----时间差"+(time2-time1));

                    }


                }



/*执行1*/
                for (int i = 0; i <list.size() ; i++) {

                    System.out.println("未保存有效且超时流出ID"+list.get(i).getCid());
                    if (sysServices.UpdateCrmdataByTimeState(89,list.get(i).getCid())==1){

                        map.put("未保存有效且超时流出ID:"+list.get(i).getCid(),list.get(i));


                                    recordServices.insertRecord(
                                            list.get(i).getCid(),
                                            list.get(i).getLastuname(),
                                            list.get(i).getLastuid(),
                                            dateTime.format(formatter),
                                            "@"+list.get(i).getLastuname()+" : 未保存有效且超时流出 ");

                    }

                }


            } catch (Exception e) {
                map.put("未保存有效报错","报错");
                throw new RuntimeException();
            }finally {


            }




            try {
                System.out.println("getScheduledtime==1 执行");
                System.out.println("已经保存的用户");
                List<CrmdataScheduled> list2 =new ArrayList<>();
                List<CrmdataScheduled> list3 =new ArrayList<>();
                for (int i = 0; i < sysServices.SelectCrmdataByscheduledtime2().size(); i++) {
                    //如果查看到时间是空 那么就是没有跟进线索 查询领取时间 和 现在时间对比

                    if(sysServices.SelectCrmdataByscheduledtime2().get(i).getTime()==null){

                        Date date1 = sdf.parse(sysServices.SelectCrmdataByscheduledtime2().get(i).getLasttime());
                        Date date2 = sdf.parse(dateTime.format(formatter));

                        long time1 = date1.getTime();
                        long time2 = date2.getTime();
                        if(time2-time1>=(long)sysServices.SelectSysByscheduledtime().getScheduledday()*86400000){

                            //判断是不是已经开单过的用户
                            if(sysServices.SelectCrmdataByscheduledOpenDan(sysServices.SelectCrmdataByscheduledtime2().get(i).getCid())==0){
                                list2.add(sysServices.SelectCrmdataByscheduledtime2().get(i));
                                /*判断执行成功后添加日志*/
//                                if(sysServices.UpdateCrmdataByTimeState(89,sysServices.SelectCrmdataByscheduledtime2().get(i).getCid())==1){
//                                    recordServices.insertRecord(
//                                            sysServices.SelectCrmdataByscheduledtime2().get(i).getCid(),
//                                            sysServices.SelectCrmdataByscheduledtime2().get(i).getLastuname(),
//                                            sysServices.SelectCrmdataByscheduledtime2().get(i).getLastuid(),
//                                            dateTime.format(formatter),
//                                            "@"+sysServices.SelectCrmdataByscheduledtime2().get(i).getLastuname()+" : 没有线索且超时流出 ");
//                                }


                            }else {

                            }



                        }


                    }else {

                        Date date1 = sdf.parse(sysServices.SelectCrmdataByscheduledtime2().get(i).getTime());
                        Date date2 = sdf.parse(dateTime.format(formatter));
                        long time1 = date1.getTime();
                        long time2 = date2.getTime();
                        if(time2-time1>(long)sysServices.SelectSysByscheduledtime().getScheduledday()*86400000 ){
                            System.out.println("有线索切超时");
                            if(sysServices.SelectCrmdataByscheduledOpenDan(sysServices.SelectCrmdataByscheduledtime2().get(i).getCid())==0){

                                list3.add(sysServices.SelectCrmdataByscheduledtime2().get(i));

//                                if(sysServices.UpdateCrmdataByTimeState(89,sysServices.SelectCrmdataByscheduledtime2().get(i).getCid())==1){
//                                    recordServices.insertRecord(
//                                            sysServices.SelectCrmdataByscheduledtime2().get(i).getCid(),
//                                            sysServices.SelectCrmdataByscheduledtime2().get(i).getLastuname(),
//                                            sysServices.SelectCrmdataByscheduledtime2().get(i).getLastuid(),
//                                            dateTime.format(formatter),
//                                            "@"+sysServices.SelectCrmdataByscheduledtime2().get(i).getLastuname()+" : 有线索且超时流出 ");
//                                }

                            }else {

                            }

                        }


                    }




                }

                /*执行2*/
                for (int i = 0; i <list2.size() ; i++) {

                    System.out.println("没有线索且超时流出ID"+list2.get(i).getCid());

                    if(sysServices.UpdateCrmdataByTimeState(89,list2.get(i).getCid())==1){
                        map.put("没有线索且超时流出ID:"+list2.get(i).getCid(),list2.get(i));
                        recordServices.insertRecord(
                                list2.get(i).getCid(),
                                list2.get(i).getLastuname(),
                                list2.get(i).getLastuid(),
                                dateTime.format(formatter),
                                "@"+list2.get(i).getLastuname()+" : 没有线索且超时流出 ");
                    }
                }

                /*执行3*/
                for (int i = 0; i <list3.size() ; i++) {

                    System.out.println("有线索且超时"+list3.get(i).getCid());

                    if(sysServices.UpdateCrmdataByTimeState(89,list3.get(i).getCid())==1){
                        map.put("有线索且超时流出ID:"+list3.get(i).getCid(),list3.get(i));
                        recordServices.insertRecord(
                                list3.get(i).getCid(),
                                list3.get(i).getLastuname(),
                                list3.get(i).getLastuid(),
                                dateTime.format(formatter),
                                "@"+list3.get(i).getLastuname()+" : 有线索且超时流出 ");
                    }
                }
            } catch (Exception e) {


           map.put("已保存有效报错","报错");

                throw new RuntimeException();
            }finally {

                sysServices.InsertCrmdata_timer(map.size(), map.toString());
            }
        } else if (sysServices.SelectSysByscheduledtime().getScheduledtime() == 0) {

            System.out.println("getScheduledtime==0 不执行");
            sysServices.InsertCrmdata_timer(0, "{管理员禁用功能}");
        }

    }
}