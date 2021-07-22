package com.example.springboot.services.Impl;

import com.example.springboot.dao.OrderAndUidMapper;
import com.example.springboot.entity.OrderAndUid;
import com.example.springboot.services.OrderAndServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderAndImpl  implements OrderAndServices {

    @Autowired
    private OrderAndUidMapper orderAndUidMapper;
    @Override
    public OrderAndUid SelectOrderAndUid(int uid, String starttime, String endtime) {
        return orderAndUidMapper.SelectOrderAndUid(uid, starttime, endtime);
    }
}
