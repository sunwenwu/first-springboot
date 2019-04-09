package com.example.firstspringboot.demo;

import java.TestJavaPath;

/**
 * @author: sunwenwu
 * @Date: 2019/3/28 15：41
 * @Description:
 */
public class TestJava {
  public static void main(String[] args) {
    //

              System.out.println(TestJava.class.getClassLoader());
      TestJavaPath p = new TestJavaPath();
      p.setName("sss");

    System.out.println(p);
  }
}
