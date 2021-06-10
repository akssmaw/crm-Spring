package com.example.springboot.services;

import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataScheduled;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotifyServices {
    public List<Crmdata> SelectCrmdateByStat_0(@Param("uid")int uid);

    public List<CrmdataScheduled> SelectCrmdateByStat_fu1(@Param("uid")int uid);


}
