package com.example.firstspringboot.dataconstract;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author :sunwenwu
 * @Date : 2021/4/17
 * @Desc :
 */
public class Test {

    public static void main(String[] args) {
        SkipList<String> list=new SkipList<String>();
        System.out.println(list);
        list.put(2, "yan");
        list.put(1, "co");
        list.put(3, "feng");
        list.put(1, "cao");//测试同一个key值
        list.put(4, "曹");
        list.put(6, "丰");
        list.put(5, "艳");
        System.out.println(list);
        System.out.println(list.size());

        System.out.println(list.search(1));
        System.out.println(list.search(4));

        System.out.println();
        Map<String,String> key = new HashMap<>();
        Map<String,String> key2 = new HashMap<>();
        key.put("","");

//        Integer key = 1;

        int h;
        int dd =  (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        System.out.println(list.hashCode());
        System.out.println(list.hashCode() >>> 16);
        System.out.println(dd);


        Object o = new Object();
        o.hashCode();

        System.out.println(decimalToBinary(list.hashCode()));
        System.out.println(decimalToBinary(list.hashCode() >>> 16));
        System.out.println(decimalToBinary(3 << 1));

        System.out.println(3 | 4);
    }

    public static String decimalToBinary(int n) {
        String str = "";
        while (n != 0) {
            str = n % 2 + str;
            n = n / 2;
        }
        return str;
    }
}
