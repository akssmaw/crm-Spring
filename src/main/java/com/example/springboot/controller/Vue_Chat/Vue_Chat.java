package com.example.springboot.controller.Vue_Chat;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.controller.Vue_Chat.md5.EnMd5;
import com.example.springboot.controller.Vue_Chat.md5.UtilToken;
import com.example.springboot.dao.chat.ChatComMapper;
import com.example.springboot.dao.chat.ChatUserMapper;

import com.example.springboot.entity.MakeRate;

import com.example.springboot.robin.WeightRandom;
import com.example.springboot.robin.WeightRoundRobin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "所有数据接口")
@Component
@RestController

@RequestMapping(value = "Vue_Chat")
public class Vue_Chat {

    @Autowired
    private ChatComMapper chatComMapper;
    @Autowired
    private ChatUserMapper chatUserMapper;

    @Autowired
    private WeightRandom weightRandom;

    @Autowired
    private UtilToken utilToken;
    //消息发送模板
    @Autowired
    private SimpMessagingTemplate simpMessageSendingOperations;


    @ApiOperation("根据公司id查询公司有多少客服")
    @PostMapping("/Chat_ComByComId/{comid}/{userip}/{useraddress}")
    public Object Chat_ComByComId(@PathVariable("comid") String comid,
                                  @PathVariable("userip")String userip,
                                  @PathVariable("useraddress")String useraddress) {
        Map<String, Object> map = new HashMap<>();
        if (chatComMapper.Chat_ComByComId(comid) != null) {


            System.out.println( chatUserMapper
                    .Chat_UserByComId(chatComMapper
                    .Chat_ComByComId(comid)
                    .getCid()) );


         if(weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId(comid).getCid()))!=0){


             System.out.println("把用户 转交给 iD的客服"+weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId(comid).getCid())));


//             map.put("data", enMd5.string2MD5(userip+"-"+comid+"-"+weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId(comid).getCid()))));
//             System.out.println(userip+"-"+comid+"-"+weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId(comid).getCid())));
//             System.out.println(enMd5.string2MD5(userip+"-"+comid+"-"+weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId(comid).getCid()))));
//             String data = "{comid:"+comid+",userip:"+userip+",chatid:"+weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId(comid).getCid()))+"}";


//
             int chid = weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId(comid).getCid()));
             System.out.println(chid+"-------------");
             Map TokenMap = new HashMap();

             TokenMap.put("UserIp",userip);

             TokenMap.put("ChatId",chid);

             TokenMap.put("ComId",comid);

             System.out.println("第一次进入客服 分配客服");
             System.out.println("客服id=="+chid);
             System.out.println("token=="+   utilToken.createToken(TokenMap));

             System.out.println("解密=="+  utilToken.verifyToken(utilToken.createToken(TokenMap)));

             map.put("code", ChatType.User_Id);
             map.put("token",utilToken.createToken(TokenMap));

             ;//将消息推送给‘、topic/ip’的客户端


             map.put("message", "访问成功");


             simpMessageSendingOperations.convertAndSend("/topic/Vue_ChatMess_ChatId/" + chid,
                     "{userip:'"+userip+"'," +
                             "createdTime:'"+System.currentTimeMillis()+"',address:'"+useraddress+"'}");


        }else {
             /*当全部用户的东西都为0的时候 抛出 */
             map.put("code", ChatType.UserWeight_Is_Null);
             map.put("message", "请联系主管开通权限");
         }



            return map;

        }

        map.put("code", ChatType.Com_Is_Null);
        map.put("message", "公司不存在");
        return map;


    }


    @ApiOperation("查询有没有这个token顺便判断token有咩有过期")
    @PostMapping ("/Chat_ComByComIdCheck/{token}")
    public Object Chat_ComByComIdCheck(@PathVariable("token")String token) {
        Map<String, String> map = new HashMap<>();
        System.out.println(token);
        if((utilToken.verifyTokenIs(token))){

            System.out.println("成功");

            System.out.println(utilToken.verifyToken(token));

            System.out.println("用户ip=="+utilToken.verifyToken(token).get(0));

            System.out.println("客服id===="+utilToken.verifyToken(token).get(1));

            System.out.println("公司id=="+utilToken.verifyToken(token).get(2));


            int chatId = Integer.parseInt((String) utilToken.verifyToken(token).get(1));


 if (chatUserMapper.chatUserById(chatId).getUserState()==0 ||
         chatUserMapper.chatUserById(chatId).getUserLogoState()==0
 ){


     System.out.println("正在更换客服");
/*更新客服*/
     Map TokenMap = new HashMap();

     TokenMap.put("UserIp",utilToken.verifyToken(token).get(0));

     TokenMap.put("ChatId",weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId((String) utilToken.verifyToken(token).get(2)).getCid())));

     TokenMap.put("ComId",utilToken.verifyToken(token).get(2));

 System.out.println("客服id=="+weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId((String) utilToken.verifyToken(token).get(2)).getCid())));
 System.out.println("token=="+   utilToken.createToken(TokenMap));
 if(
         weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId((String) utilToken.verifyToken(token).get(2)).getCid()))==0
 ){
     map.put("message","当前客服都不在线");
     map.put("code","1001");
     return map;
 }
     System.out.println("客服更换成功");
 map.put("message","已更换客服");
     map.put("code","1002");
     map.put("oldToken",token);
 map.put("token",utilToken.createToken(TokenMap));

 return map;

 }
            map.put("code","1003");
            map.put("token",token);
            map.put("message","当前客服在线");

            return map;

        }

        System.out.println("失败");
        map.put("code","1004");
        map.put("message","验证失败");/*没有这个公司或者 不存在*/
return map;
    }
}