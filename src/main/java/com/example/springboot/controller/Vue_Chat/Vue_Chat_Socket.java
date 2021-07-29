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

    @PostMapping("/AllotByToken/{token}/{message}")
    public void AllotByToken( @PathVariable("token")String token,
                       @PathVariable("message")String message) {

        ;//将消息推送给‘、topic/ip’的客户端
        simpMessageSendingOperations.convertAndSend("/topic/Vue_ChatMess_ChatId/" + token, message);

    }
    @PostMapping("/AllotByIp/{ip}/{message}")
    public void AllotByIp( @PathVariable("ip")String ip,
                       @PathVariable("message")String message) {

        ;//将消息推送给‘、topic/ip’的客户端
        simpMessageSendingOperations.convertAndSend("/topic/Vue_ChatMess/" + ip, message);

    }

}
