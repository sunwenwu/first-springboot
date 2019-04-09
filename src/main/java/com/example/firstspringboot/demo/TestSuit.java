package com.example.firstspringboot.demo;

/**
 * @author: sunwenwu
 * @Date: 2019/4/2 18：01
 * @Description:
 */
public class TestSuit {

  public static void main(String[] args) {

     /* for (int i = 1;i<=20;i++) {
          int b = i + 1;
      System.out.println("i="+i+" "+(b*b - i*i));
      }*/

      for (int i = 1;i<=4;i++) {
          int a = 11;
          int b = 12;
      System.out.println("i="+i+" "+(Math.pow(b,i) - Math.pow(a,i)));
      }

    System.out.println(Math.pow(10,3));
    System.out.println(Math.pow(10,4));
    System.out.println(Math.pow(10,5));
    System.out.println(Math.pow(10,6));
    //
  }
}
