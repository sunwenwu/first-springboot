package com.example.firstspringboot.demo;





import java.sql.Driver;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: sunwenwu
 * @Date: 2019/1/23 10：50
 * @Description:
 */
public class MessageFormatTest {

    public static final String WAIT_PICKUP_OFFLINE_PRICE_ORDER_NOTIFY_MSG_4_CUSTOMER = "您的订单已支付完成，取货码为{0}，收货时请将提货码出示给小店";

    public static void main(String[] args) {
    //

//     String customerMsg = (String) MessageFormat.format(WAIT_PICKUP_OFFLINE_PRICE_ORDER_NOTIFY_MSG_4_CUSTOMER);


   /*     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println(simpleDateFormat.format(new Date()));

        String ss = "1111";
        String[] s = ss.split("_");

    System.out.println(s[0]);
    System.out.println(s[1]);*/

    System.out.println(Integer.MAX_VALUE);



        ServiceLoader<Driver> d= ServiceLoader.load(Driver.class);
        Iterator<Driver> it = d.iterator();
        System.out.println(Integer.MIN_VALUE);
        while(it.hasNext()){
            Driver dd =it.next();
            System.out.println(dd.toString());//oracle.jdbc.OracleDriver@498e2a42
        }

        Short aa = null;
        if ((short)1 == aa) {
      System.out.println("正常");
        }
    }
}
