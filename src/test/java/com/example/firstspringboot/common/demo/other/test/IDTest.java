package com.example.firstspringboot.common.demo.other.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: sunwenwu
 * @Date: 2018/11/15 13：32
 * @Description:
 */
public class IDTest {
    public static void main(String[] args) {
        String code = "YH"+getTimeStr()+""+(int)((Math.random()*9+1)*10000);
        String code2 = "TH"+getTimeStr()+""+(int)((Math.random()*9+1)*10000);

        System.out.println("要货单号："+code);
        System.out.println("退货单号："+code2);

        StringBuffer sb = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());

        sb.append("YH").append(format).append((int)((Math.random()*9+1)*10000));
        System.out.println("要货单号："+sb.toString());




    }

    public static String getTimeStr(){
        Calendar cal = Calendar.getInstance();
        String timw = cal.get(Calendar.YEAR)+""
                +(cal.get(Calendar.MONTH)+1)+""
                +cal.get(Calendar.DAY_OF_MONTH)+""
                +cal.get(Calendar.HOUR_OF_DAY)+""
                +cal.get(Calendar.MINUTE)+""
                +cal.get(Calendar.SECOND)+"";
        return timw;
    }
}
