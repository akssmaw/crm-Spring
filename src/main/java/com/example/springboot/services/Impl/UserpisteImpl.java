package com.example.springboot.services.Impl;

import com.example.springboot.dao.UserpisteMapper;
import com.example.springboot.entity.Userpiste;
import com.example.springboot.services.UserpisteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserpisteImpl implements UserpisteServices {

    @Autowired
    private UserpisteMapper userpisteMapper;
    @Override
    public List<Userpiste> UserpisteAll(int cid) {
        return userpisteMapper.UserpisteAll(cid);
    }

    @Override
    public int InsertUserpiste(int cid, String ptime, String pcon, int uid, String uname) {
        return userpisteMapper.InsertUserpiste(cid, ptime, pcon, uid, uname);
    }

    @Override
    public int DeleteUserpisteByPid(int pid) {
        return userpisteMapper.DeleteUserpisteByPid(pid);
    }


}
