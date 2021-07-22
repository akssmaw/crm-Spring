package com.example.springboot.services.Impl;

import com.example.springboot.dao.Getuser2Mapper;
import com.example.springboot.services.Getuser2Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Getuser2Impl implements Getuser2Services {
    @Autowired
    private Getuser2Mapper getuser2Mapper;
    @Override
    public int UidAndTimeByGetnext2(int uid, String gettime) {
        return getuser2Mapper.UidAndTimeByGetnext2(uid, gettime);
    }

    @Override
    public int insertGetuser2(int cid, int uid, String gettime) {
        return getuser2Mapper.insertGetuser2(cid, uid, gettime);
    }

    @Override
    public int selectGetuser2Count(int cid) {
        return getuser2Mapper.selectGetuser2Count(cid);
    }
}
