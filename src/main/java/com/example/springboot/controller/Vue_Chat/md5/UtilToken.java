package com.example.springboot.controller.Vue_Chat.md5;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class UtilToken {
    private static final long EXPIRE_TIME = 15 * 60 * 1000;//默认15分钟
    //私钥
    private static final String TOKEN_SECRET = "privateKey";

    /**
     * 生成签名，15分钟过期
     * @param **username**
     * @param **password**
     * @return
     */
    public static String createToken(Map map) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串

            return JWT.create()
                    .withHeader(header)
                    .withClaim("UserIp",  map.get("UserIp").toString())
                    .withClaim("ComId",  map.get("ComId").toString())
                    .withClaim("ChatId",  map.get("ChatId").toString())
//                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成token，自定义过期时间 毫秒
     * @param **username**
     * @param **password**
     * @return
     */
    public String createToken(String userId,long expireDate) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + expireDate);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userip", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public List<Object> verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);



        List<Object> list = new ArrayList();
        list.add(jwt.getClaim("UserIp").asString());
            list.add(jwt.getClaim("ChatId").asString());
            list.add(jwt.getClaim("ComId").asString());



            return list;
        } catch (Exception e){
            List<Object> list = new ArrayList();
            list.add(0);
            list.add(0);
            list.add(0);

            return list;
        }

    }

    public boolean verifyTokenIs(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            Map map = new HashMap();

            map.put("UserIp",jwt.getClaim("UserIp").asString());
            map.put("ChatId",jwt.getClaim("ChatId").asString());
            map.put("ComId",jwt.getClaim("ComId").asString());


            return true;
        } catch (Exception e){

            return false;
        }
    }
//    public static void main(String[] args) {
//
//        Map map = new HashMap();
//
//        map.put("UserIp","woshiip");
//        map.put("ChatId","woshiid");
//        map.put("ComId","woshigongsi");
//        String token = createToken(map);
//        System.out.println("token == " + token);
//
//        System.out.println(verifyToken("eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJVc2VySXAiOiJ3b3NoaWlwIiwiQ29tSWQiOiJ3b3NoaWdvbmdzaSIsIkNoYXRJZCI6Indvc2hpaWQifQ.jx9xhDS5Wy6mdp15qz0Robc8o2Zbs4Rj8CO0HcNrsjk"));
//
//
//
//    }

}
