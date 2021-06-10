package com.example.springboot.services;

import com.example.springboot.entity.UserStatis;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


import java.util.List;

public interface UserStatisServices {
    List<UserStatis> SelectByDefault(@Param("stattime")String stattime, @Param("endtime")String endtime  );


    List<UserStatis> SelectByPrepay(@Param("stattime")String stattime,@Param("endtime")String endtime  );

    List<UserStatis> SelectByGroup(@Param("stattime")String stattime,@Param("endtime")String endtime  );

}
