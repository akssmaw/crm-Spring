package com.example.springboot.services.Impl;

import com.example.springboot.dao.notify.NotifyMapper;
import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataScheduled;
import com.example.springboot.services.NotifyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotifyImpl implements NotifyServices {
    @Autowired
    private NotifyMapper notifyMapper;
    @Override
    public List<Crmdata> SelectCrmdateByStat_0(int uid) {
        return notifyMapper.SelectCrmdateByStat_0(uid);
    }

    @Override
    public List<CrmdataScheduled> SelectCrmdateByStat_fu1(int uid) {
        return notifyMapper.SelectCrmdateByStat_fu1(uid);
    }
}
