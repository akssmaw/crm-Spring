package com.example.springboot.services.Impl;

import com.example.springboot.dao.TotalMapper;
import com.example.springboot.entity.Total;
import com.example.springboot.services.TotalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalImpl implements TotalServices {
    @Autowired
    private TotalMapper totalMapper;
    @Override
    public List<Total> TotalAll(String starttime, String endtime) {
        return totalMapper.TotalAll(starttime, endtime);
    }
}
