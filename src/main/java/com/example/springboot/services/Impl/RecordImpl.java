package com.example.springboot.services.Impl;

import com.example.springboot.dao.RecordMapper;
import com.example.springboot.entity.Record;
import com.example.springboot.services.RecordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordImpl implements RecordServices {

    @Autowired
    private RecordMapper recordMapper;



    public int insertRecord(int cid, String lastuname, int lastuid, String lasttime, String lastcon) {



        return recordMapper.insertRecord(cid,lastuname,lastuid,lasttime,lastcon);


    }

    @Override
    public List<Record> SelectRecordById(int cid) {
        return recordMapper.SelectRecordById(cid);
    }
}
