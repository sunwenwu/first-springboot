package com.example.firstspringboot.common.demo.other.test;

/**
 * @author: sunwenwu
 * @Date: 2019/6/10 17：05
 * @Description:
 */
public class SoutTest {
  public static void main(String[] args) {
    String s = "I am a student !";

      String[] s1 = s.split(" ");

      StringBuffer stringBuffer = new StringBuffer("");
      for (int i = s1.length;i>=1;i--) {
          if (i == 1) {
              stringBuffer.append(s1[i-1]);
          } else {
              stringBuffer.append(s1[i-1]).append(" ");
          }
      }

    System.out.println(stringBuffer.toString());
  }
}
