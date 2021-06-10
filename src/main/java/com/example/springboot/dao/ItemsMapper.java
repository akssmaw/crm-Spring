package com.example.springboot.dao;

import com.example.springboot.entity.Items;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemsMapper {
//查询公共的导航条
    public List<Items> ItemsAll();


}
