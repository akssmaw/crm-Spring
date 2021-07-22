package com.example.springboot.dao;

import com.example.springboot.entity.Children;
import com.example.springboot.entity.Items;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

   public List<User> UserAll(@Param("day")String day,@Param("day2")String day2);

   public  int SelectUserStateByUid(@Param("uid")int uid);
   //增加销售
   public int AddUser( User user);
   //判断销售存在
   public int AddUserByUname(@Param("uname")String uname);

   public User LoginUser(User user);

   //查询全部销售名字 返回uid uname字段
   public List<Children> UserAllByUname();

   //根据uid查询是不是管理员
   public int UserAllByuid(@Param("uid")int uid);

   //根据uid 有多少领取个数
   public User UserAllByuid2(@Param("uid")int uid);

   public int UpdateUserAllAddCount();

   public int UpdateUserNotAddCount();

public int UpdateUserUstate(@Param("ustate")int ustate);



}
