package com.example.springboot.controller;

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

    @GetMapping("/getParam")
    public String getParam(){

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        for (RequestMappingInfo info : methodMap.keySet()){

            Set<String> urlSet = info.getPatternsCondition().getPatterns();
            // 获取全部请求方式
            Set<RequestMethod> Methods = info.getMethodsCondition().getMethods();
            System.out.println(Methods.toString());

            for (String url : urlSet){
                // 加上自己的域名和端口号，就可以直接调用
                urlList.add(url);
            }
        }
        return urlList.toString();
    }
}
