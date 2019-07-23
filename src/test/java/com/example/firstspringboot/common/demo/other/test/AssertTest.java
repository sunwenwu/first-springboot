package com.example.firstspringboot.common.demo.other.test;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: sunwenwu
 * @Date: 2019/6/10 15：52
 * @Description:
 */
public class AssertTest {

    public final  static String  yyyy_MM_dd_format="yyyy-MM-dd";


    public static void main(String[] args) throws Exception{
    //
      String s = null;

//      Assert.notNull(s,"将xml字符串转成实体时出错，rootElt数据格式错误");

    System.out.println(s);


        Date date = toDate("0000-00-00", yyyy_MM_dd_format);



    System.out.println(date);
    System.out.println(new SimpleDateFormat(yyyy_MM_dd_format).format(date).toString());
    }

    public static Date toDate(String strDate, String format) throws ParseException {
        if(StringUtils.isEmpty(strDate)) {
            return null;
        }

        return new SimpleDateFormat(format).parse(strDate);
    }
}
