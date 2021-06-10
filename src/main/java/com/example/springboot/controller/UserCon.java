package com.example.springboot.controller;

import com.example.springboot.entity.Children;
import com.example.springboot.entity.Children2;
import com.example.springboot.entity.User;
import com.example.springboot.services.GetuserServices;
import com.example.springboot.services.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "用户接口")
/*http://localhost:8081*/
@RestController
@RequestMapping(value = "user")
public class UserCon {

    @Autowired
    private UserServices userServices;

    @Autowired
    private GetuserServices getuserServices;

    @ApiOperation("添加用户")
    /*用户存在返回2    用户成功返回1  用户字段错误返回userMapper.AddUser(user)*/
    @PostMapping("/adduser")
    public Object addUser( @RequestBody @Valid User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        if(userServices.AddUser(user)==1){
            return 1;
        }else if(userServices.AddUser(user)==2){
            return 2;
        }
        return userServices.AddUser(user);
    }
    //查询全部销售 表user
    @PostMapping("/UserAll/{day}/{day2}")
    public Object addUser(@PathVariable("day")String day,@PathVariable("day2")String day2) {

        return  userServices.UserAll(day, day2);
    }
    @ApiOperation("验证")
    @PostMapping("/getUser/{id}/{name}")
    public User getUser(@PathVariable int id ,@PathVariable String name) {
        System.out.println(id);
        System.out.println(name);
        User user = new User();


        return user;
    }
    @ApiOperation("登陆")
    @PostMapping("/loginUser")
    public Object loginUser( @RequestBody @Valid User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        return userServices.LoginUser(user);
    }


    @ApiOperation("查询全部销售名单")
    @PostMapping("/allbyuname")
    public Object allbyuname() {

        return userServices.UserAllByUname() ;

    }

    @ApiOperation("查询全部销售名单2")
    @PostMapping("/allbyuname2")
    public Object allbyuname2() {

        List<Children2> list = new ArrayList<>();

        for (int i = 0; i < userServices.UserAllByUname().size() ; i++) {

            list.add(new Children2(userServices.UserAllByUname().get(i).getValue(),userServices.UserAllByUname().get(i).getLabel()));

        }
        return  list;
    }

    @ApiOperation("根据id和time查询和管理员设置 的销售差值 领取客户 计算差")
    @PostMapping("/getuserByIdAndTime/{uid}/{gettime}")
    public Object getuserByIdAndTime(@PathVariable int uid ,@PathVariable String gettime) {
        System.out.println("uid"+uid);
        System.out.println( "可领取多少"+getuserServices.UidAndTimeByGetnext(uid,gettime));
        System.out.println("gettime"+gettime);

        if(getuserServices.UidAndTimeByGetnext(uid,gettime)==0){

            return  0;
        }else {

            return  getuserServices.UidAndTimeByGetnext(uid,gettime);

        }

    }
    @ApiOperation("一键增加 全部首字领取额度")
    @PostMapping("/UpdateUserAllAddCount")
    public Object UpdateUserAllAddCount() {

        return userServices.UpdateUserAllAddCount() ;

    }
    @ApiOperation("一键减去 全部首字领取额度")
    @PostMapping("/UpdateUserNotAddCount")
    public Object UpdateUserNotAddCount() {

        return userServices.UpdateUserNotAddCount() ;

    }
    @ApiOperation("一建设置是否可以登陆")
    @PostMapping("/UpdateUserUstate/{ustate}")
    public Object UpdateUserUstate(@PathVariable("ustate")int ustate) {

        return userServices.UpdateUserUstate(ustate) ;

    }

    @ApiOperation("查询账号是不是呗禁用状态")
    @PostMapping("/SelectUserStateByUid/{uid}")
    public Object SelectUserStateByUid(@PathVariable("uid")int uid) {

        return userServices.SelectUserStateByUid(uid) ;

    }

}
