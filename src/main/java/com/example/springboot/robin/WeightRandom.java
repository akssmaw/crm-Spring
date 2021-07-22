package com.example.springboot.robin;

import com.example.springboot.entity.chat.chat_user;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WeightRandom  {
    static List<chat_user> categorys = new ArrayList<chat_user>();
    private static Random random = new Random();

    public static void initData(List<chat_user> user) {
        categorys = user;

    }
public int run(List<chat_user> user){

    initData(user);
    Integer weightSum = 0;

    for (chat_user wc : categorys) {
        weightSum += wc.getUserWeight();
    }

    if (weightSum <= 0) {
        System.err.println("Error: weightSum=" + weightSum.toString());
        return 0;
    }
    Integer n = random.nextInt(weightSum); // n in [0, weightSum)
    Integer m = 0;
    System.out.println("size"+categorys.size());
    int userid = 0;
    for (int i = 0; i <categorys.size() ; i++) {
        System.out.println("进入for");
        if (m <= n && n < m + categorys.get(i).getUserWeight()) {

            if( categorys.get(i).getUserState()==0|| categorys.get(i).getUserLogoState()==0){

                System.out.println("跳出"+categorys.get(i));

            }else {
                System.out.println("This Random Category is "+ categorys.get(i).getUserName());

                userid =  categorys.get(i).getId();
                System.out.println("userid"+userid);
                System.out.println("客服id"+categorys.get(i).getId());
                break;
            }

        }
        m +=  categorys.get(i).getUserWeight();

    }

    System.out.println(m);
    System.out.println("出来的uid"+userid);
    return userid;
}



}


