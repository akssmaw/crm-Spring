package com.example.springboot.controller.Vue_Chat;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Api(tags = "WebSocket通知接口")
@Component
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "Socket")
public class Vue_Chat_Socket {
    @Autowired
    private SimpMessagingTemplate simpMessageSendingOperations;//消息发送模板
    //客户分配

    @PostMapping("/Demo/{message}/{token}")
    public void Allot(@PathVariable("message")String message,
                      @PathVariable("token")String token) {

        ;//将消息推送给‘、topic/ip’的客户端
        simpMessageSendingOperations.convertAndSend("/topic/Vue_ChatMess/" + token, message);

    }


}
