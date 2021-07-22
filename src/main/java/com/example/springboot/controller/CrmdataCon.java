package com.example.springboot.controller;


import com.example.springboot.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "所有数据接口")

@RestController
@RequestMapping(value = "crmdata")
public class CrmdataCon {


    @Autowired
    private SimpMessagingTemplate simpMessageSendingOperations;//消息发送模板

    @Autowired
    private CrmdataServices crmdataServices;
    @Autowired
    private RecordServices recordServices;
    @Autowired
    private GetuserServices getuserServices;
    @Autowired
    private Getuser2Services getuser2Services;
    @Autowired
    private SetuserServices setuserServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private GetIpServices getIpServices;



    @ApiOperation("获取全部")
    @PostMapping("/All/{uid}/{page}")
    public Object CrmdataAll(@PathVariable int uid,@PathVariable int page) {

        System.out.println("用户进来的id"+uid);



        return  crmdataServices.CrmdataByUid(uid,page);

    }
    @ApiOperation("获取全部2")
    @PostMapping("/All2/{stat}/{page}")
    public Object CrmdataAll2(@PathVariable int stat,@PathVariable int page) {



        return  crmdataServices.CrmdataAll2(stat, page);

    }

    @ApiOperation("获取全部3根据 lastuid=0 ")
    @PostMapping("/All3/{lastuid}/{page}")
    public Object CrmdataAll3(@PathVariable int lastuid,@PathVariable int page) {



        return  crmdataServices.CrmdataAll3(lastuid, page);

    }

    @ApiOperation("查询已经被流入公海点多数据 ")
    @PostMapping("/All4/{page}")
    public Object CrmdataAll4( @PathVariable int page) {



        return  crmdataServices.CrmdataAll4(page);

    }
    @ApiOperation("查询ajax进来的数据 ")
    @PostMapping("/All5/{page}")
    public Object CrmdataAll5( @PathVariable int page) {



        return  crmdataServices.CrmdataAll5(page);

    }
    @ApiOperation("修改客户分配")
    @PostMapping("/updata/{uid}/{uname}/{list}/{datetime}")
    public Object CrmdataAll(@PathVariable int uid,@PathVariable String uname,@PathVariable List<Object> list,@PathVariable String datetime) {
        System.out.println("Uid"+uid);
        System.out.println("Uname"+uname);
        System.out.println("Ulist"+list);
        System.out.println("Udatetime"+datetime);

        for (int i = 0; i <list.size() ; i++) {


            int listid= Integer.parseInt(list.get(i).toString());


            //实现分配记录
            recordServices.insertRecord(listid,uname,uid,datetime,"管理员分配给 : "+"@"+uname+"");


            //实现修改name id 时间 根据cid

            crmdataServices.UpdateCrmdataById(uname,uid,datetime,listid);

        }


        return  list;

    }

    @ApiOperation("客户修改备注信息 增加日志")
    @PostMapping("/updatabycon/{lastuid}/{lastuname}/{cid}/{datetime}/{con}")
    public Object updatabycon(@PathVariable int lastuid,@PathVariable String lastuname,@PathVariable int cid,@PathVariable String datetime,@PathVariable String con) {

            //实现分配记

        return   recordServices.insertRecord(cid,lastuname,lastuid,datetime,"@"+lastuname+" :  "+con+" ");

    }


    @ApiOperation("根据cid单查数据")
    @PostMapping("/bycid/{cid}")
    public Object CrmdataByCid(@PathVariable int cid) {

        System.out.println("单查cid进来的id"+cid);



        return  crmdataServices.CrmdataByCid(cid);

    }
    @ApiOperation("根据cid修改stat显示状态")
    @PostMapping("/updatastatbycid/{cid}/{stat}")
    public Object UpdataStatByCid(@PathVariable int cid,@PathVariable int stat) {

        System.out.println("修改cid进来的id"+cid);
        System.out.println("修改的stat"+stat);


        return  crmdataServices.UpdateStatByByCid(cid, stat);

    }
    //流入公海
    @ApiOperation("根据cid修改stat显示状态")
    @PostMapping("/UpdataGongHaiBycid/{list}")
    public Object UpdataGongHaiBycid(@PathVariable List<Object> list) {
        System.out.println(list);

        for (int i = 0; i <list.size() ; i++) {
            int cid= Integer.parseInt(list.get(i).toString());
            crmdataServices.UpdateStatByByCid(cid, 9999);
        }
        return list;

    }
    //放弃后流入公海  88
    @ApiOperation("根据cid修改stat显示状态")
    @PostMapping("/UpdataGongHaiBycidDan/{cid}/{uid}")
    public Object UpdataGongHaiBycidDan(@PathVariable int cid,@PathVariable int uid) {

        /*记录表
        * */
        setuserServices.UpdateSetUserByCidAndUid(cid,uid,88);

//        修改主表 放弃公海
        return  crmdataServices.UpdateStatByByCid(cid, 88);

    }

    @ApiOperation(" 领取客户  修改显示状态 显示uid 时间  增加getuser表数据")
    @PostMapping("/UpdataCrmdataAndInsertGetuser/{uid}/{uname}/{utime}/{ztime}/{cid}")
    public Object UpdataCrmdataAndInsertGetuser(@PathVariable int uid ,@PathVariable String uname,@PathVariable String utime,@PathVariable String ztime,@PathVariable int cid ) {
        System.out.println("进入");
        /*mess=0  没有领取次数了
        *mess=1  手速慢了
        * msg=2 领取成功
        * msg=3 程序错误
        * */

        Map<String, Integer> map = new HashMap();
        //判断领取次数


        int savecount = crmdataServices.SelectYouXiaoCount(uid);

        if(savecount>=userServices.UserAllByuid2(uid).getSavecount()){
            map.put("msg",4);

            System.out.println(66);
            return  map;
        }else {
            int getnext= getuserServices.UidAndTimeByGetnext(uid,ztime);
            System.out.println("有效没有超过");
            if(getnext<=0){

                map.put("msg",0);
                map.put("getnext",getnext);

                return  map;
            }else {

                //判断是不是已经被领取了
                if(crmdataServices.CrmdataByCid(cid).getStat()!=0 && crmdataServices.CrmdataByCid(cid).getStat()!=-1){

                    //判断是不是领取成功了
                    if(getuserServices.insertGetuser(cid,uid,utime)==1 &&  crmdataServices.UpdateCrmdataById2(uname, uid, utime, cid)==1){

                        map.put("msg",2);
                        map.put("getnext",getnext-1);
                    }else {

                        map.put("msg",3);
                        map.put("getnext",getnext);
                    }


                }else {
                    map.put("msg",1);
                    map.put("getnext",getnext);
                    return  map;
                }



                return  map;

            }
        }
        //判断还有多少个领取次数




    }


    @ApiOperation(" 领取首资客户  修改显示状态 显示uid 时间  增加getuser表数据 进来 的数据state = -2 ")
    @PostMapping("/UpdataCrmdataAndInsertGetuser2/{uid}/{uname}/{utime}/{ztime}/{cid}")
    public Object UpdataCrmdataAndInsertGetuser2(@PathVariable int uid ,@PathVariable String uname,@PathVariable String utime,@PathVariable String ztime,@PathVariable int cid ) {

        /*mess=0  没有领取次数了
         *mess=1  手速慢了
         * msg=2 领取成功
         * msg=3 程序错误
         * */

        Map<String, Integer> map = new HashMap();
        //判断领取次数
        int savecount = crmdataServices.SelectYouXiaoCount(uid);


        //判断还有多少个领取次数
        if(savecount>=userServices.UserAllByuid2(uid).getSavecount()){
            map.put("msg",4);

            System.out.println(66);
            return  map;
        }else {
            int getnext= getuser2Services.UidAndTimeByGetnext2(uid,ztime);
            if(getnext<=0){

                map.put("msg",0);
                map.put("getnext",getnext);

                return  map;
            }else {


                //判断是不是已经被领取了
                if(crmdataServices.CrmdataByCid(cid).getStat()!=0 && getuser2Services.selectGetuser2Count(cid)==0){

                    //判断是不是领取成功了
                    try{
                        if(getuser2Services.insertGetuser2(cid,uid,utime)==1  &&  crmdataServices.UpdateCrmdataById2(uname, uid, utime, cid)==1){

                            map.put("msg",2);
                            map.put("getnext",getnext-1);
                        }else {

                            map.put("msg",3);
                            map.put("getnext",getnext);
                        }

                    } catch(Exception e){


                        if(e instanceof DuplicateKeyException) {

                            map.put("msg",1);
                            map.put("getnext",getnext);
                            System.out.println("报错");
                        }
                    }



                }else {
                    map.put("msg",1);
                    map.put("getnext",getnext);
                    return  map;
                }



                return  map;

            }

        }



    }

    @ApiOperation("查询全部保存有效客源")
    @PostMapping("/SelectYouXiao/{uid}/{page}")
    public Object SelectYouXiao(@PathVariable int uid,@PathVariable int page) {

        return crmdataServices.SelectYouXiao(uid, page);

    }

    @ApiOperation("根据cid修改stat显示状态")
    @PostMapping("/SelectYouXiaoSearh/{uid}/{phone}/{color}/{page}")
    public Object SelectYouXiaoSearh(@PathVariable int uid ,
                                     @PathVariable(value = "phone",required = false)String phone,
                                     @PathVariable(value = "color",required = false) int color,
                                     @PathVariable int page) {

        System.out.println("uid"+uid);
        System.out.println("phone"+phone);
        System.out.println("color"+color);
        System.out.println("page"+page);
        return crmdataServices.SelectYouXiaoSearh(uid,phone,color,page);

    }
    @ApiOperation("查询已经保存的客源 用于查询订单")
    @PostMapping("/Selectpastuser/{uid}/{page}")
    public Object Selectpastuser(@PathVariable int uid,@PathVariable int page) {

        return crmdataServices.Selectpastuser(uid, page);

    }

    @ApiOperation("查询已经保存的客源 搜索功能 用于查询订单")
    @PostMapping("/SelectpastuserSearch/{uid}/{phone}")
    public Object SelectpastuserSearch(@PathVariable int uid,@PathVariable String phone) {

        return crmdataServices.SelectpastuserSearch(uid,phone);

    }

    @ApiOperation("自己增加操作")
    @PostMapping("/InsertCrmdata/{phone}/{source}/{url}/{con}/{plankey}/{uname}/{uid}")
    public Object InsertCrmdata(@PathVariable String phone,
                                @PathVariable String source,
                                @PathVariable String url,
                                @PathVariable String con,
                                @PathVariable String plankey,
                                @PathVariable String uname,
                                @PathVariable int uid,
                                HttpServletResponse response, HttpServletRequest request) {
Map<String, String> map  =new HashMap<>();
       if( crmdataServices.SelectCrmdataByPhone(phone)>=1){

           map.put("data","用户已存在");
           return map;
       }else {
           map.put("data","自己录入成功");


           crmdataServices.InsertCrmdata(phone,source,url,con,plankey, uname, uid, getIpServices.IpServices(request));
           return map;
       }


    }


    @ApiOperation("增加接口提交的用户")
    @PostMapping("/InsertCrmdata2/{phone}/{source}/{url}/{con}/{plankey}")
    public Object InsertCrmdata2( HttpServletRequest request,
                                  @PathVariable String phone,
                                 @PathVariable String source,
                                 @PathVariable String url,
                                 @PathVariable String con,
                                 @PathVariable String plankey
                                 ) {
        Map<String, String> map = new HashMap<>();
        try{
            if( crmdataServices.SelectCrmdataByPhone(phone)>=1){
                map.put("code","0");
                map.put("msg","用户已存在");

            }else {
                if(crmdataServices.InsertCrmdata2(phone, source, url, con, getIpServices.IpServices(request), plankey)==0){
                    map.put("code","0");
                    map.put("msg","限制增加");
                }else {
                    map.put("code","1");
                    map.put("msg","添加成功");
                    simpMessageSendingOperations.convertAndSend("/topic/Allot", 2);//将消息推送给‘、topic/ip’的客户端
                }
            }
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                map.put("code","0");
                map.put("msg","程序报错");

            }
            System.out.println(e);
        }




        return  map;

    }

    @ApiOperation("客服接口提交的用户")
    @PostMapping("/InsertCrmdata3/{phone}/{source}/{url}/{con}/{plankey}")
    public Object InsertCrmdata3( HttpServletRequest request,
                                  @PathVariable String phone,
                                  @PathVariable String source,
                                  @PathVariable String url,
                                  @PathVariable String con,
                                  @PathVariable String plankey
    ) {
        Map<String, String> map = new HashMap<>();

        if( crmdataServices.SelectCrmdataByPhone(phone)>=1){
            map.put("code","0");
            map.put("msg","用户已存在");

        }else {
            if(crmdataServices.InsertCrmdata3(phone, source, url, con, getIpServices.IpServices(request), plankey)==1){
                map.put("code","1");
                map.put("msg","添加成功");
                simpMessageSendingOperations.convertAndSend("/topic/Allot", 3);//将消息推送给‘、topic/ip’的客户端
            }else {

                map.put("code","0");
                map.put("msg","增加失败");
            }
        }


        return  map;

    }
    @ApiOperation(" 根据cid修改备注 con")
    @PostMapping("/UpdateCrmDataByIdCon/{cid}/{con}")
    public Object UpdateCrmDataByIdCon(@PathVariable int cid,@PathVariable String con) {

        return crmdataServices.UpdateCrmDataByIdCon(cid, con);

    }

    @ApiOperation(" 运营统计")
    @PostMapping("/SelectCrmdataBySource/{source}/{starttime}/{endtime}/{page}")
    public Object SelectCrmdataBySource(@PathVariable("source") List<Object> source,
                                        @PathVariable("starttime")String starttime,
                                        @PathVariable("endtime")String endtime,
                                        @PathVariable("page")int page
                                        ) {

        System.out.println("666666666666666666666666666666666666666666");
        System.out.println(source.get(0));
        String nowsource = "'"+"%"+source.get(0)+"%"+source.get(1)+"%"+"'";
        System.out.println(nowsource);
        System.out.println(crmdataServices.SelectCrmdataCount(nowsource, starttime, endtime));
        Map<String, Object> map = new HashMap<>();
        map.put("count",crmdataServices.SelectCrmdataCount(nowsource, starttime, endtime));
        map.put("data",crmdataServices.SelectCrmdataBySource(nowsource,starttime, endtime, page));
        return map;

    }
    @ApiOperation(" 运营统计2  没用来源的时候")
    @PostMapping("/SelectCrmdataBySourceBySourceIsNull/{starttime}/{endtime}/{page}")
    public Object SelectCrmdataBySourceBySourceIsNull(
                                        @PathVariable("starttime")String starttime,
                                        @PathVariable("endtime")String endtime,
                                        @PathVariable("page")int page
    ) {

        Map<String, Object> map = new HashMap<>();
        map.put("count",crmdataServices.SelectCrmdataCountBySourceIsNull( starttime, endtime));
        map.put("data",crmdataServices.SelectCrmdataBySourceBySourceIsNull(starttime, endtime, page));

        return map;

    }
    @ApiOperation(" 运营统计2  搜索")
    @PostMapping("/SelectCrmdataBySourceIsSearch/{phone}")
    public Object SelectCrmdataBySourceIsSearch(
            @PathVariable("phone")String phone
    ) {


        return crmdataServices.SelectCrmdataBySourceIsSearch(phone);

    }

    @ApiOperation("查询全部保存有效客源")
    @PostMapping("/UpdataColorByCid/{cid}/{color}")
    public Object UpdataColorByCid(@PathVariable int cid,@PathVariable int color) {

        return crmdataServices.UpdataColorByCid(cid, color);

    }

}
