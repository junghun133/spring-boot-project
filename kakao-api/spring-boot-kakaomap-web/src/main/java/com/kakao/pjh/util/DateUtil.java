package com.kakao.pjh.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static boolean compareDate(Date before){
        try {
            Calendar calendar = Calendar.getInstance();
            //before + 1day
            calendar.setTime(before);
            calendar.add(Calendar.DATE, 1);
            int result = calendar.getTime().compareTo(new Date());

            if (result < 0)
                return true;

            return false;
        }catch (Exception e){
            return false;
        }
    }
}
