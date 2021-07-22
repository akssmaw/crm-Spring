package com.example.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.entity.Children;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;
import java.util.*;
@Api(tags = "版本号Con")
@RestController
@RequestMapping(value = "Version")
public class VersionCon {



    @SneakyThrows
    @GetMapping("/1")
    public Object itemsAll() {

        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();

        Map<String, String> map =new HashMap();
        String rootPath = System.getProperty("user.dir");
        MavenXpp3Reader reader = new MavenXpp3Reader();
        String myPom = rootPath + File.separator + "pom.xml";
        Model model = reader.read(new FileReader(myPom));
        String version = model.getVersion();//获取版本号
        System.out.println(model.getName());
        System.out.println(model.getGroupId());



        map.put("SpringVersion",springVersion);
        map.put("SpringBootVersion",springBootVersion);
        map.put("Version",version);
        map.put("JDK_url",System.getProperty("user.home"));
        map.put("JDK_version",System.getProperty("java.version"));
        map.put("Windows", System.getProperty("os.name"));

        return map;

    }
    @Autowired
    WebApplicationContext applicationContext;

    /*json对象  */
    @GetMapping("/getParam/{data}")
    public String getParam(@PathVariable String data, HttpServletRequest request){

        HttpSession session3 = request.getSession();
        session3.setAttribute("user", "我是value");
        HttpSession session4 = request.getSession();
        session3.setAttribute("user2", "我是value2");

//        JSONArray json = (JSONArray) JSONArray.parse(data);
//        for (int i = 0; i <json.size() ; i++) {
//
//            System.out.println( json.get(i));
//            JSONObject jo = (JSONObject)json.get(i);
//            System.out.println(jo.getString("label"));
//            System.out.println(jo.getInteger("value"));
//
//        }
//获取session
        HttpSession   session   =   request.getSession();
// 获取session中所有的键值
        Enumeration<String> attrs = session.getAttributeNames();
// 遍历attrs中的
        while(attrs.hasMoreElements()){
// 获取session键值
            String name = attrs.nextElement().toString();
            // 根据键值取session中的值
            Object vakue = session.getAttribute(name);
            // 打印结果
            System.out.println("------" + name + ":" + vakue +"--------\n");}

        return session.getAttribute("user").toString();
    }
}
