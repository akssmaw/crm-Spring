package com.example.springboot.services;

import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataScheduled;
import com.example.springboot.entity.Sys;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysServices {
    //根据
    Sys SelectSysByscheduledtime();

    //定时查询
    public List<Crmdata> SelectCrmdataByscheduledtime();
    //定时查询2 已经保存了有效 查询是不是及时 更近了
    public List<CrmdataScheduled> SelectCrmdataByscheduledtime2();

    public int SelectCrmdataByscheduledOpenDan(@Param("cid")int cid);

    public int UpdateCrmdataByTimeState(@Param("stat")int stat,@Param("cid")int cid);

    public int InsertCrmdata_timer(@Param("size")int size,@Param("cont")String cont);


    //修改定时
    public int UpdateSysByScheduledtime(@Param("scheduledtime")int scheduledtime,@Param("scheduledday")int scheduledday);


}
