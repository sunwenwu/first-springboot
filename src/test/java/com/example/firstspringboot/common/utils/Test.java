package com.example.firstspringboot.common.utils;

import java.util.*;

/**
 * @author: sunwenwu
 * @Date: 2019/3/21 17：38
 * @Description:
 */
public class Test {

  public static void main(String[] args) {


    Map<Integer,String> hashMap = new HashMap<>();
    Map<Integer,String> linkMap = new LinkedHashMap<>();



    for (int i = 1;i<100;i++) {
      hashMap.put(i,i+"="+i);
      linkMap.put(i,i+"="+i);
    }

    Set<Map.Entry<Integer, String>> entries = hashMap.entrySet();
    Set<Map.Entry<Integer, String>> entries2 = linkMap.entrySet();
    for (Map.Entry<Integer, String> entry :entries) {
      System.out.println(entry.getValue());
    }
    System.out.println("=================");

    for (Map.Entry<Integer, String> entry2 :entries2) {
      System.out.println(entry2.getValue());
    }
  }
}
