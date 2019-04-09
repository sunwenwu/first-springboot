package com.example.firstspringboot.common.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: sunwenwu
 * @Date: 2019/3/6 14：33
 * @Description:
 */
public class ExceptionTest {

  public static void main(String[] args) {
    //

     /* run();

    System.out.println("还能执行？？");*/


      List<DemoTest> list = new ArrayList<>();
      /*list.add(new DemoTest("aaa",1));
      list.add(new DemoTest("bbb",2));
      list.add(new DemoTest("ccc",3));*/

      List<String> orderNoList = list.stream().map(DemoTest::getName).collect(Collectors.toList());


      Iterator<String> iterator = orderNoList.iterator();

      while (iterator.hasNext()) {
      System.out.println(iterator.next());
      }

      for (String s : orderNoList) {
      System.out.println(s);
      }

  }


  public static void run () {

      try{
          throw new RuntimeException("test");
      } finally{
      System.out.println("....");
      }
  }

  static class DemoTest {
      private String name;

      private Integer id;

      public DemoTest(String name, Integer id) {
          this.name = name;
          this.id = id;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public Integer getId() {
          return id;
      }

      public void setId(Integer id) {
          this.id = id;
      }
  }
}
