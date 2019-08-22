package com.example.firstspringboot.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@author   sunwenwu
 *@Date 2019年8月19日
 */
@Slf4j
public class DateUtil {

    private final  static String deafult_format="yyyy-MM-dd HH:mm:ss";

    public static Date stringToDate(String dateStrt) throws ParseException  {

        return stringToDate(dateStrt,null) ;
    }

    public static Date stringToDate(String dateStr, String format) throws ParseException{
        if(StringUtils.isBlank(dateStr)){
            return  null;
        }
        if(StringUtils.isBlank(format)) {
            format=deafult_format;
        }
        Date date= null;
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            log.error("日期转换异常",e.getMessage());
            throw e;
        }
        return date ;
    }

}
