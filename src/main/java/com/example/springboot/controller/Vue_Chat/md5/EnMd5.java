package com.example.springboot.controller.Vue_Chat.md5;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EnMd5 {


    //正则表达式，用于匹配
    private final static Pattern pattern = Pattern.compile("\\d+");

    private final static String charset="utf-8";

    //加密处理
    public static String encode(String src,String key) {
        try {
            //得到一个指定的编码格式的字节数组，Linux和windows默认的编码格式不同，所以要指定特定的编码
            byte[] data = src.getBytes(charset);
            byte[] keys = key.getBytes();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                //结合key和相应的数据进行加密操作,ofxx的作用是补码，byte是8bits，而int是32bits
                int n = (0xff & data[i]) + (0xff & keys[i % keys.length]);
                sb.append("@" + n);
            }
            return sb.toString();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return src;
    }

    //解密处理
    public static String decode(String src, String key) {
        if(src == null || src.length() == 0){
            return src;
        }
        //正则表达式字符串匹配
        Matcher m = pattern.matcher(src);

        List<Integer> list = new ArrayList<Integer>();
        //find方法(部分匹配):尝试去发现输入串中是否匹配相应的子串
        while (m.find()) {
            try {
                //返回匹配到的子字符串
                String group = m.group();
                list.add(Integer.valueOf(group));
            } catch (Exception e) {
                e.printStackTrace();
                return src;
            }
        }

        //如果有匹配的字符串
        if (list.size() > 0) {
            try {
                byte[] data = new byte[list.size()];
                byte[] keys = key.getBytes();
                //相对于加密过程的解密过程
                for (int i = 0; i < data.length; i++) {
                    data[i] = (byte) (list.get(i) - (0xff & keys[i % keys.length]));
                }
                return new String(data, charset);
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            return src;
        } else {
            return src;
        }

    }

    static final String encode="UTF-8";
    //对传递的数据进行md5加密
    public static String sign(String signStr){
        //DigestUtils.md5Hex()方法Java调用Apache commons codec实现md5加密，计算MD5摘要并返回值为32个字符的十六进制字符串
        return shuffleSign(DigestUtils.md5Hex(signStr));
    }
    private static byte[][] shufflePos=new byte[][]{{1,13},{5,17},{7,23}};
    private static String shuffleSign(String src){
        if(src == null || src.length() == 0){
            return src;
        }
        try {
            //得到一个指定的编码格式的字节数组
            byte[] bytes=src.getBytes("utf-8");
            byte temp;
            //循环遍历shufflePos，将二维数组中每位一维数组中的每个元素进行换位
            for(int i=0; i<shufflePos.length; i++){
                temp=bytes[shufflePos[i][0]];
                bytes[shufflePos[i][0]]=bytes[shufflePos[i][1]];
                bytes[shufflePos[i][1]]=temp;
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException e) {
            return src;
        }
    }


// 测试主函数

    public static void main(String[] args) {
        //双方约定好的签名
        final String key ="asd";
        //数据
        final String data ="{a:21,a:21,a:21a:21,a:21}";

        //对数据和签名进行加密处理

        System.out.println("加密为:"+encode(data, key));

        //对加密后的数据进行md5处理
        System.out.println("对数据进行md5加密后在进行加密"+sign("nt_data=" + data));

        //对进过md5处理后的数据进行签名后加密
        String nt_data2 = encode(sign("nt_data=" + data), key);
        System.out.println(nt_data2);

        //对加密后的数据进行解密处理
        String sign_en = decode(encode(data, key), key);
        System.out.println("解密"+sign_en);


    }

}
