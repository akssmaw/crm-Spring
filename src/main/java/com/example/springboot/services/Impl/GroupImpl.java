package com.example.springboot.services.Impl;

import com.example.springboot.dao.GroupMapper;
import com.example.springboot.entity.Make;
import com.example.springboot.entity.MakeMoney;
import com.example.springboot.entity.MakeRate;
import com.example.springboot.services.GroupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupImpl  implements GroupServices {
    @Autowired
    private GroupMapper groupMapper;
    @Override
    public List<Object> SelectAllGroup() {
        return groupMapper.SelectAllGroup();
    }

    @Override
    public List<Make> SelectAllMakeCount(String time1, String time2, int group) {
        return groupMapper.SelectAllMakeCount(time1, time2, group);
    }

    @Override
    public MakeRate selectMakeRate(String time1, String time2, int group) {
        return groupMapper.selectMakeRate(time1, time2, group);
    }

    @Override
    public MakeMoney selectMakeMoney(String time1, String time2, int group) {
        return groupMapper.selectMakeMoney(time1, time2, group);
    }
}
