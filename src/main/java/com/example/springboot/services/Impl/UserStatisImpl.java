package com.example.springboot.services.Impl;

import com.example.springboot.dao.UserStatisMapper;
import com.example.springboot.entity.UserStatis;
import com.example.springboot.services.UserStatisServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserStatisImpl implements UserStatisServices {
    @Autowired
    private UserStatisMapper userStatisMapper;
    @Override
    public List<UserStatis> SelectByDefault(String stattime, String endtime) {
        return userStatisMapper.SelectByDefault(stattime, endtime);
    }

    @Override
    public List<UserStatis> SelectByPrepay(String stattime, String endtime) {
        return userStatisMapper.SelectByPrepay(stattime, endtime);
    }

    @Override
    public List<UserStatis> SelectByGroup(String stattime, String endtime) {
        return userStatisMapper.SelectByGroup(stattime, endtime);
    }
}
