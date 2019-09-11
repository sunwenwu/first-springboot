package com.example.firstspringboot.common.bean;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: sunwenwu
 * @Date: 2018/12/18 16：20
 * @Description:
 */
@Data
public class LombokDemo {
    private String name;

    private Innertest innertest;

    class Innertest{
        private String age;
    }


    public static void main(String[] args) {
//        LombokDemo lombokDemo = new LombokDemo();

        System.out.println(getCurrDateStr(new Date(),"yyyyMMdd"));
        System.out.println(getCurrDateStr(new Date(),"HHmmss"));


    }




    public static String getCurrDateStr(Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
