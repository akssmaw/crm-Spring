package com.example.springboot.dao;

import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataCount;
import com.example.springboot.entity.SourceSize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CrmdataMapper {


//判断用户有没有存在
int SelectCrmdataByPhone(@Param("phone")String phone);

//增加
    int InsertCrmdata(@Param("phone")String phone,

                      @Param("source")String source,
                      @Param("url")String url,
                      @Param("con")String con,
                      @Param("plankey")String plankey,
                      @Param("uid")int uid,
                      @Param("uname")String uname,
                      @Param("ip")String ip);

    //查询ip有多少条
    int SelectCrmdataByIp(@Param("ip")String ip);

    //增加
    int InsertCrmdata2(@Param("phone")String phone,
                       @Param("source")String source,
                       @Param("url")String url,
                       @Param("con")String con,
                       @Param("ip")String ip,
                       @Param("plankey")String plankey
                       );
    //增加3
    int InsertCrmdata3(@Param("phone")String phone,
                       @Param("source")String source,
                       @Param("url")String url,
                       @Param("con")String con,
                       @Param("ip")String ip,
                       @Param("plankey")String plankey
    );


    //查询全部客户
    public List<Crmdata> CrmdataAll(@Param("page")int page);

    //查询全部客户
    public List<Crmdata> CrmdataAll2(@Param("stat")int stat,@Param("page")int page);


    //查询没有分配的客户
    public List<Crmdata> CrmdataAll3(@Param("lastuid")int lastuid,@Param("page")int page);

    //查询全部已经被流入公海的客户
    public List<Crmdata> CrmdataAll4(@Param("page")int page);

    //查询全部已经被流入公海2的客户
    public List<Crmdata> CrmdataAll5(@Param("page")int page);

    //根据uid查询销售的客户
    public List<Crmdata> CrmdataByUid(@Param("uid")int uid,@Param("page")int page);


    //根据Cid单查数据
    public Crmdata CrmdataByCid(@Param("cid")int cid);


    //根据id修 分配的销售名字 id 时间
    public int UpdateCrmdataById(@Param("lastuname")String lastuname,@Param("lastuid")int lastuid,@Param("lasttime")String lasttime,@Param("cid")int cid);

    //领取客户 根据id 单个领取客户
    //根据id修 分配的销售名字 id 时间 修改 stat =  0
    public int UpdateCrmdataById2(@Param("lastuname")String lastuname,@Param("lastuid")int lastuid,@Param("lasttime")String lasttime,@Param("cid")int cid);

    //修改学院的状态 stat 默认= 0   修改后！=0
    public  int UpdateStatByByCid(@Param("cid")int cid,@Param("stat")int stat);

    //根据销售id查询已标记为有效客户 并且主表是-1   状态2表是0
    public  List<Crmdata> SelectYouXiao(@Param("uid")int uid,@Param("page")int page);

    public  List<Crmdata> SelectYouXiaoByColorSort(@Param("uid")int uid,@Param("page")int page);

    //根据销售id查询已标记为有效客户 并且主表是-1   状态2表是0 查询
    public  List<Crmdata> SelectYouXiaoSearh(@Param("uid")int uid,@Param("phone")String phone);
    //查询保存了多少有效客户
    public int SelectYouXiaoCount(@Param("uid")int uid);

    public List<Crmdata> Selectpastuser(@Param("uid")int uid,@Param("page") int page);

    public List<Crmdata> SelectpastuserSearch(@Param("uid")int uid,@Param("phone") String phone);

    //根据cid修改备注 con
    public int UpdateCrmDataByIdCon(@Param("cid")int cid,@Param("con")String con);

    public List<Crmdata> SelectCrmdataBySource(
                                               @Param("source")String source,
                                               @Param("starttime")String starttime,
                                               @Param("endtime")String endtime,
                                               @Param("page")int page);

    public SourceSize SelectCrmdataBySourceSize(
            @Param("source")String source,
            @Param("starttime")String starttime,
            @Param("endtime")String endtime);

public CrmdataCount SelectCrmdataCount(  @Param("source")String source,

                                         @Param("starttime")String starttime,
                                         @Param("endtime")String endtime);

public  int UpdataColorByCid(@Param("cid")int cid,@Param("color")int color);





}
