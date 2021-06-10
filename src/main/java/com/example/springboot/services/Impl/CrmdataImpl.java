package com.example.springboot.services.Impl;

import com.example.springboot.dao.CrmdataMapper;
import com.example.springboot.dao.UserMapper;
import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataCount;
import com.example.springboot.entity.SourceSize;
import com.example.springboot.services.CrmdataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmdataImpl implements CrmdataServices {


    @Autowired
    private CrmdataMapper crmdataMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int SelectCrmdataByPhone(String phone) {
        return crmdataMapper.SelectCrmdataByPhone(phone);
    }

    @Override
    public int InsertCrmdata(String phone, String source, String url, String con, String plankey, String uname, int uid, String ip) {
        return crmdataMapper.InsertCrmdata(phone, source, url, con, plankey, uid, uname, ip);
    }


    @Override
    public int SelectCrmdataByIp(String ip) {
        return crmdataMapper.SelectCrmdataByIp(ip);
    }

    @Override
    public int InsertCrmdata2(String phone, String source, String url, String con, String ip, String plankey) {
        if(SelectCrmdataByIp(ip)>=3){

            return 0;
        }

        return crmdataMapper.InsertCrmdata2(phone, source, url, con, ip, plankey);
    }

    @Override
    public int InsertCrmdata3(String phone, String source, String url, String con, String ip, String plankey) {
        return crmdataMapper.InsertCrmdata3(phone, source, url, con, ip, plankey);
    }

    public List<Crmdata> CrmdataAll(int page) {
        return crmdataMapper.CrmdataAll(10*(page-1));
    }

    @Override
    public List<Crmdata> CrmdataAll2(int stat, int page) {
        return crmdataMapper.CrmdataAll2(stat, 10*(page-1));
    }

    @Override
    public List<Crmdata> CrmdataAll3(int lastuid, int page) {
        return crmdataMapper.CrmdataAll3(lastuid,10*(page-1));
    }

    @Override
    public List<Crmdata> CrmdataAll4(int page) {
        return crmdataMapper.CrmdataAll4(10*(page-1));
    }

    @Override
    public List<Crmdata> CrmdataAll5(int page) {
        return crmdataMapper.CrmdataAll5(10*(page-1));
    }

    @Override
    public List<Crmdata> CrmdataByUid(int uid,int page) {
        if(userMapper.UserAllByuid(uid)==0){

            return crmdataMapper.CrmdataAll(10*(page-1));

        }else {

            return crmdataMapper.CrmdataByUid(uid,10*(page-1));


        }



    }

    @Override
    public Crmdata CrmdataByCid(int cid) {
        return crmdataMapper.CrmdataByCid(cid);
    }

    @Override
    public int UpdateCrmdataById(String lastuname, int lastuid, String lasttime, int cid) {
        return crmdataMapper.UpdateCrmdataById(lastuname,lastuid,lasttime,cid);
    }

    @Override
    public int UpdateCrmdataById2(String lastuname, int lastuid, String lasttime, int cid) {
        return crmdataMapper.UpdateCrmdataById2(lastuname, lastuid, lasttime, cid);
    }

    @Override
    public int UpdateStatByByCid(int cid, int stat) {
        return crmdataMapper.UpdateStatByByCid(cid, stat);
    }

    @Override
    public List<Crmdata> SelectYouXiao(int uid, int page) {
        return crmdataMapper.SelectYouXiao(uid, 10*(page-1));
    }

    @Override
    public List<Crmdata> SelectYouXiaoSearh(int uid, String phone) {
        return crmdataMapper.SelectYouXiaoSearh(uid, phone);
    }

    @Override
    public int SelectYouXiaoCount(int uid) {
        return crmdataMapper.SelectYouXiaoCount(uid);
    }

    @Override
    public List<Crmdata> SelectYouXiaoByColorSort(int uid, int page) {
        return crmdataMapper.SelectYouXiaoByColorSort(uid, 10*(page-1));
    }

    @Override
    public List<Crmdata> Selectpastuser(int uid, int page) {
        return crmdataMapper.Selectpastuser(uid,10*(page-1));
    }

    @Override
    public List<Crmdata> SelectpastuserSearch(int uid, String phone) {
        return crmdataMapper.SelectpastuserSearch(uid, phone);
    }

    @Override
    public int UpdateCrmDataByIdCon(int cid, String con) {
        return crmdataMapper.UpdateCrmDataByIdCon(cid, con);
    }

    @Override
    public List<Crmdata> SelectCrmdataBySource(String source, String starttime, String endtime, int page) {
        return crmdataMapper.SelectCrmdataBySource(source, starttime, endtime, 10*(page-1));
    }

    @Override
    public SourceSize SelectCrmdataBySourceSize(String source, String starttime, String endtime) {
        return crmdataMapper.SelectCrmdataBySourceSize(source, starttime, endtime);
    }

    @Override
    public CrmdataCount SelectCrmdataCount(String source, String starttime, String endtime) {
        return crmdataMapper.SelectCrmdataCount(source, starttime, endtime);
    }

    @Override
    public int UpdataColorByCid(int cid, int color) {
        return crmdataMapper.UpdataColorByCid(cid, color);
    }


}
