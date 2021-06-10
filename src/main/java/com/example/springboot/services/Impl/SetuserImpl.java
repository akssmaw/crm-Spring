package com.example.springboot.services.Impl;

import com.example.springboot.dao.SetuserMapper;
import com.example.springboot.services.SetuserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetuserImpl implements SetuserServices {

    @Autowired
    private SetuserMapper setuserMapper;

    @Override
    public int InsertSetUser(int cid, int uid, int iid, String settime) {
        return setuserMapper.InsertSetUser(cid, uid, iid, settime);
    }

    @Override
    public int UpdateSetUserByCidAndUid(int cid, int uid,int state) {
        return setuserMapper.UpdateSetUserByCidAndUid(cid, uid,state);
    }
}
