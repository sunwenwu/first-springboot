package com.example.firstspringboot.common.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author: sunwenwu
 * @Date: 2019/6/20 15：20
 * @Description:
 */
public class TestDD {

    private static Logger logger = LoggerFactory.getLogger(TestDD.class);


    public static void main(String[] args) {

/*

      List<String> strings = Arrays.asList("11", "22", "33");

      for (String ssss :strings) {


        System.out.println("Q:"+System.identityHashCode(ssss));
      }


      try{
          int i = 1/0;
      }catch (Exception e){
          logger.error("syncOrderData增量失败：{}", e);

      }*/

/*    Integer aa = null;

    String test = "aa";

        Integer i = test == null ? new Integer(0) : aa;

    System.out.println(i);*/


        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("1");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()){
            if ("2".equals(iterator.next())) {
                iterator.remove();
            }
        }

    System.out.println(list);



        List<String> stringList = new ArrayList<>();

        stringList.add("1");
        stringList.add("2");
        stringList.add("3");


        String[] s = new String[stringList.size()];
        System.out.println(s[0]);

        stringList.toArray(s);

        System.out.println(s[0]);
    }
}
