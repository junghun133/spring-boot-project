package com.kakao.pjh.util;

import java.util.Random;

public class TokenUtil {
    public static String getRandomToken() {
        Random rnd =new Random();
        StringBuffer buf =new StringBuffer();
        for(int i=0;i<20;i++){
            if(rnd.nextBoolean()){
                buf.append((char)((int)(rnd.nextInt(26))+97));
            }else{
                buf.append((rnd.nextInt(10)));
            }
        }
        return buf.toString();
    }
}
