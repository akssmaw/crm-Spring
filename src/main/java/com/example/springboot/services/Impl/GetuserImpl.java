package com.example.springboot.services.Impl;

import com.example.springboot.dao.GetuserMapper;
import com.example.springboot.dao.ItemsMapper;
import com.example.springboot.services.GetuserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetuserImpl  implements GetuserServices {

    @Autowired
    private GetuserMapper getuserMapper;

    public int UidAndTimeByGetnext(int uid, String gettime) {

        return getuserMapper.UidAndTimeByGetnext(uid, gettime);
    }

    @Override
    public int insertGetuser(int cid, int uid, String gettime) {
        return getuserMapper.insertGetuser(cid, uid, gettime);
    }
}
