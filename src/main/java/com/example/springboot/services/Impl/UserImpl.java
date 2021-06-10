package com.example.springboot.services.Impl;

import com.example.springboot.MD5.Md5;
import com.example.springboot.dao.UserMapper;
import com.example.springboot.entity.Children;
import com.example.springboot.entity.User;
import com.example.springboot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserImpl implements UserServices {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Md5 md5;


    @Override
    public List<User> UserAll(String day, String day2) {
        return userMapper.UserAll(day, day2);
    }

    @Override
    public int SelectUserStateByUid(int uid) {
        return userMapper.SelectUserStateByUid(uid);
    }

    //用户存在  返回2 用户成功返回1  用户字段错误返回userMapper.AddUser(user)
    @Override
    public int AddUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if(userMapper.AddUserByUname(user.getUname())==1){

            return 2;

        }else {
            //返回2有用户存在
            user.setUpassword(md5.EncoderByMd5(user.getUpassword()));


            if(userMapper.AddUser(user)==1){
                /*操作成功返回1*/
                return 1;

            }else {
                /*操作失败返回0*/
                return userMapper.AddUser(user);

            }

        }


    }

    @Override
    public int AddUserByUname(String Uname) {
        return 0;

    }

    @Override
    public Object LoginUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {


        System.out.println("密码"+user.getUpassword());
        user.setUpassword(md5.EncoderByMd5(user.getUpassword()));
        System.out.println("密码"+user.getUpassword());
        if(userMapper.LoginUser(user)==null){
            return 0;
        }else {

            return userMapper.LoginUser(user);
        }




    }

    @Override
    public List<Children> UserAllByUname() {

        return userMapper.UserAllByUname();
    }

    @Override
    public int UserAllByuid(int uid) {

        return userMapper.UserAllByuid(uid);
    }

    @Override
    public User UserAllByuid2(int uid) {
        return userMapper.UserAllByuid2(uid);
    }

    @Override
    public int UpdateUserAllAddCount() {
        return userMapper.UpdateUserAllAddCount();
    }

    @Override
    public int UpdateUserNotAddCount() {
        return userMapper.UpdateUserNotAddCount();
    }

    @Override
    public int UpdateUserUstate(int ustate) {
        return userMapper.UpdateUserUstate(ustate);
    }


}
