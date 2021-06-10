package com.example.springboot.services.Impl;

import com.example.springboot.dao.SatisfactionMapper;
import com.example.springboot.entity.Satisfaction;
import com.example.springboot.services.SatisfactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatisfactionImpl implements SatisfactionServices {

    @Autowired
    private SatisfactionMapper satisfactionMapper;
    @Override

    public List<Satisfaction> SatisfactionAll() {
        return satisfactionMapper.SatisfactionAll();
    }

    @Override
    public List<Satisfaction> SatisfactionByUidAndTime(String time1, String time2, int uid) {
        return satisfactionMapper.SatisfactionByUidAndTime(time1, time2, uid);
    }
}
