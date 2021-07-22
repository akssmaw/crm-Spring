package com.example.springboot.controller;

import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "WebSocket客服接口")
@Component
@RestController
@RequestMapping(value = "Socket/Chat")
public class ChatCon {
    @Autowired
    private SimpMessagingTemplate simpMessageSendingOperations;//消息发送模板
    //客户分配
    @RequestMapping(value = "/Chat", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public void Allot(@Param("message")String message) {

        simpMessageSendingOperations.convertAndSend("/topic/ChatMess", "66");//将消息推送给‘、topic/ip’的客户端


    }
}
