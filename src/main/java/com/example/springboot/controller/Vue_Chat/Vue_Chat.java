package com.example.springboot.controller.Vue_Chat;

import com.example.springboot.controller.Vue_Chat.md5.EnMd5;
import com.example.springboot.controller.Vue_Chat.md5.UtilToken;
import com.example.springboot.dao.chat.ChatComMapper;
import com.example.springboot.dao.chat.ChatUserMapper;

import com.example.springboot.entity.MakeRate;

import com.example.springboot.robin.WeightRandom;
import com.example.springboot.robin.WeightRoundRobin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "所有数据接口")

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

    @ApiOperation("根据公司id查询公司有多少客服")
    @PostMapping("/Chat_ComByComId/{comid}/{userip}")
    public Object Chat_ComByComId(@PathVariable("comid") String comid,@PathVariable("userip")String userip ) {
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
             Map TokenMap = new HashMap();

             TokenMap.put("UserIp",userip);
             TokenMap.put("ChatId",weightRandom.run(chatUserMapper.Chat_UserByComId(chatComMapper.Chat_ComByComId(comid).getCid())));
             TokenMap.put("ComId",comid);


             System.out.println("token=="+    UtilToken.createToken(TokenMap));
             System.out.println("解密=="+    UtilToken.verifyToken("eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJVc2VySXAiOiIxOTIuMTY4LjEuMSIsIkNvbUlkIjoiNTQzODc1NzQ4IiwiQ2hhdElkIjoiMiJ9.0F7EUEzjqjTfSuMl_7hdMGxLeurytVDakOTnoAoAZ_4"));

             map.put("code", ChatType.User_Id);
             map.put("token",UtilToken.createToken(TokenMap));
             map.put("message", "访问成功");

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

        System.out.println(token);
        if((utilToken.verifyTokenIs(token)==true)){

            System.out.println(utilToken.verifyToken(token));


            return true;
        }

        System.out.println("失败");

return false;
    }
}