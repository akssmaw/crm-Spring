package com.example.springboot.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment ){
        Profiles profiles =Profiles.of("dev","test");
        boolean flag = environment.acceptsProfiles(profiles);
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Shui Shou")
             .enable(flag) //enable配置是否自动启动swagger 如果为False则为不启动，浏览器中不能访问Swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot.controller"))
                //.paths(PathSelectors.ant("/hello/**"))
                .build();//构建者模式
    }
    /**
     * 配置Swagger信息 apiinfo
     * @return
     */
    private ApiInfo apiInfo(){
        //配置作者信息
        Contact DEFAULT_CONTACT = new Contact("ShuiShou", "http://www.baidu.com", "aimuyaoxi@163.com");
        return  new ApiInfo(
                "水兽 的Swagger API文档",
                "水兽",
                "v1.0",
                "http://www.baidu.com",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}