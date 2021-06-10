package com.example.springboot.services.Impl;

import com.example.springboot.dao.PrepayMapper;
import com.example.springboot.entity.Prepay;
import com.example.springboot.services.PrepayServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrepayImpl implements PrepayServices {
    @Autowired
    private PrepayMapper prepayMapper;
    @Override
    public int insertPrepay(int oid, String pcon, String account, double prepay) {
        return prepayMapper.insertPrepay(oid, pcon, account, prepay);
    }

    @Override
    public List<Prepay> selectPrepay(int oid) {
        return prepayMapper.selectPrepay(oid);
    }
}
