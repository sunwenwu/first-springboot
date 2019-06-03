package com.example.firstspringboot.common.demo.other.test;

import java.util.regex.Pattern;

/**
 * @author: sunwenwu
 * @Date: 2019/4/25 10：41
 * @Description:
 */
public class ZhengZe {

  public static void main(String[] args) {

//      Pattern pattern = new Pattern("开单金额");
//      boolean matches = Pattern.matches("^([0-9]{1,}[.][0-9]*)$");
      Pattern compile = Pattern.compile("^([0-9]{1,}[.][0-9]{2})$");
      Pattern compile2 = Pattern.compile("[0-1]{1}$");

      String s = "1.01";
      System.out.println(s +":"+compile.matcher(s).matches());

      String s2 = "11";
      System.out.println(s2 +":"+compile2.matcher(s2).matches());

  }
}
