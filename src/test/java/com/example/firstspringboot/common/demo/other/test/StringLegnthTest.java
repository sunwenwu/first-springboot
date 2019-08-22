package com.example.firstspringboot.common.demo.other.test;

import com.example.firstspringboot.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Date;

@Slf4j
public class StringLegnthTest {

    public static void main(String[] args) throws Exception{
        String s = "";
        String s2 = "12AB哈哈";

        System.out.println(s.length());
        System.out.println(s2.length());


        System.out.println(String.format("%s/authorize/auth?user_id=%s","hh","22"));

        String url = MessageFormat.format("{0}/authorize/auth?user_id={1}", "99","66");

        System.out.println(url);


        log.info("A:{},B:{}1566288956737",1,2);

        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
        System.out.println(DateUtil.stringToDate("2019-08-20 16:21:01").getTime());
    }
}
