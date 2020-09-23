package com.kakao.pjh.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DateUtilTest {

    @Test
    public void compareDateTest() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date passedDate = dateFormat.parse("20200924130000");
        Date day1before = dateFormat.parse("20200922115900");
        Date hour1before = dateFormat.parse("20200923110000");
        Date time1before = dateFormat.parse("20200923115900");
        assertTrue(DateUtil.compareDate(day1before));
        assertFalse(DateUtil.compareDate(hour1before));
        assertFalse(DateUtil.compareDate(time1before));
    }
}