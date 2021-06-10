package com.example.springboot.services.Impl;

import com.example.springboot.dao.sys.SysMapper;
import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataScheduled;
import com.example.springboot.entity.Sys;
import com.example.springboot.services.SysServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SysImpl implements SysServices {

    @Autowired
    private SysMapper sysMapper;

    @Override

    public Sys SelectSysByscheduledtime()  {

        return sysMapper.SelectSysByscheduledtime();
    }

    @Override
    public List<Crmdata> SelectCrmdataByscheduledtime() {
        return sysMapper.SelectCrmdataByscheduledtime();
    }

    @Override
    public List<CrmdataScheduled> SelectCrmdataByscheduledtime2() {
        return sysMapper.SelectCrmdataByscheduledtime2();
    }

    @Override
    public int SelectCrmdataByscheduledOpenDan(int cid) {
        return sysMapper.SelectCrmdataByscheduledOpenDan(cid);
    }

    @Override
    public int UpdateCrmdataByTimeState(int stat, int cid) {
        return sysMapper.UpdateCrmdataByTimeState(stat, cid);
    }

    @Override
    public int InsertCrmdata_timer(int size, String cont) {
        return sysMapper.InsertCrmdata_timer(size, cont);
    }

    @Override
    public int UpdateSysByScheduledtime(int scheduledtime, int scheduledday) {
        return sysMapper.UpdateSysByScheduledtime(scheduledtime, scheduledday);
    }


}
