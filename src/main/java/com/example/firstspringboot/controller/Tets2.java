package com.example.firstspringboot.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author :sunwenwu
 * @Date : 2020/9/22 15:31
 * @Description :
 */
public class Tets2 {



    public static void main(String[] args) {
       Integer aa = new Integer(2);
       Integer bb = new Integer(2);

       Long cc = new Long(3);
       Long ee = new Long(8);

       BigDecimal dd = new BigDecimal(3);

        System.out.println(aa.equals(bb));
        System.out.println(cc.equals(dd.longValue()));

        System.out.println(cc+ee);
    }
}
