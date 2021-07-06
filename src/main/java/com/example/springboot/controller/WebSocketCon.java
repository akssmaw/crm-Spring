package com.example.springboot.controller;

import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
@Api(tags = "WebSocket通知接口")
@Component
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "Socket")
public class WebSocketCon {
    @Autowired
    private SimpMessagingTemplate simpMessageSendingOperations;//消息发送模板
    //客户分配

    @PostMapping("/Allot/{message}")
    public void Allot(@PathVariable("message")int message) {

        simpMessageSendingOperations.convertAndSend("/topic/Allot", message);//将消息推送给‘、topic/ip’的客户端


    }

    @RequestMapping(value = "/Refresh", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public void Refresh() {

        simpMessageSendingOperations.convertAndSend("/topic/Refresh", "页面刷新");//将消息推送给‘、topic/ip’的客户端


    }

    @RequestMapping(value = "/GetuserById/{message}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public void GetuserById(@PathVariable String message) {
        System.out.println(message);

        simpMessageSendingOperations.convertAndSend("/topic/GetuserById", message);//将消息推送给‘、topic/ip’的客户端


    }

}
