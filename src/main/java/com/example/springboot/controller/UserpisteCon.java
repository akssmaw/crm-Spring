package com.example.springboot.controller;

import com.example.springboot.services.UserpisteServices;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "线索接口")

@RestController
@RequestMapping(value = "piste")
public class UserpisteCon {
    @Autowired
    private UserpisteServices userpisteServices;
    @PostMapping("/UserpisteAll/{cid}")
    public Object UserpisteAll(@PathVariable int cid ) {


        return   userpisteServices.UserpisteAll(cid);

    }
    @PostMapping("/InsertUserpiste/{cid}/{ptime}/{pcon}/{uid}/{uname}")
    public Object InsertUserpiste(@PathVariable("cid")int cid, @PathVariable("ptime")String ptime,@PathVariable("pcon")String pcon, @PathVariable("uid")int uid, @PathVariable("uname")String uname) {


        return   userpisteServices.InsertUserpiste(cid, ptime, pcon, uid, uname);

    }
    @PostMapping("/DeleteUserpisteByPid/{pid}")
    public Object InsertUserpiste(@PathVariable("pid")int pid) {


        return   userpisteServices.DeleteUserpisteByPid(pid);

    }
}
