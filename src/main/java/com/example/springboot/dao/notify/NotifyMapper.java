package com.example.springboot.dao.notify;

import com.example.springboot.entity.Crmdata;
import com.example.springboot.entity.CrmdataScheduled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NotifyMapper {

    public List<Crmdata> SelectCrmdateByStat_0(@Param("uid")int uid);

    public List<CrmdataScheduled> SelectCrmdateByStat_fu1(@Param("uid")int uid);


}
