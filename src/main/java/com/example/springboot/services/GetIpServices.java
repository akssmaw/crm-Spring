package com.example.springboot.services;

import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

public interface GetIpServices {

    String IpServices(@Param("request") HttpServletRequest request);
}
