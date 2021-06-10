package com.example.springboot.dao.sys;

import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataScheduled;
import com.example.springboot.entity.Sys;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysMapper {

    //根据
     Sys SelectSysByscheduledtime();

    //定时查询1 stat 为0 证明还没有选择有效无效
    public List<Crmdata> SelectCrmdataByscheduledtime();
    //定时查询2 已经保存了有效 查询是不是及时 更近了
    public List<CrmdataScheduled> SelectCrmdataByscheduledtime2();
    //查询是不是开单过
    public int SelectCrmdataByscheduledOpenDan(@Param("cid")int cid);
//修改状态成89表示自动给流出
    public int UpdateCrmdataByTimeState(@Param("stat")int stat,@Param("cid")int cid);

    public int InsertCrmdata_timer(@Param("size")int size,@Param("cont")String cont);


//修改定时
    public int UpdateSysByScheduledtime(@Param("scheduledtime")int scheduledtime,@Param("scheduledday")int scheduledday);


}
