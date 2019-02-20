package com.example.firstspringboot.demo;


import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author: sunwenwu
 * @Date: 2019/1/7 16：58
 * @Description:
 */
public class ClassLoaderTest {

  public static void main(String[] args) throws Exception {

      Thread.currentThread().setContextClassLoader(Thread.currentThread().getContextClassLoader().getParent());
      Class<?> aClass = new MyClassLoader().loadClass("com.example.firstspringboot.demo.String2");
      Object o1 = aClass.newInstance();

      Method run2 = aClass.getMethod("run", String.class);
      run2.invoke(o1,"zhijie好了吗");
//      System.out.println(o1.getClass().getClassLoader());

    /*  Class<?> c = Class.forName("com.example.firstspringboot.demo.String2", true, new MyClassLoader());

      Object o = c.newInstance();

      Method run = c.getMethod("run", String.class);
      run.invoke(o,"好了吗");
    System.out.println(c.getClassLoader());*/

    String str = "https://qrcode.startcharge.com/r?12345678";
    String str2 = "https://qrcode.startcharge.com/r?123456789";
    String str3 = "https://qrcode.startcharge.com/r?1234567890";

    String regEx = "^https://qrcode\\.startcharge\\.com/r\\?([0-9]{8}|[0-9]{10})$";
    Pattern pattern = Pattern.compile(regEx);

    System.out.println(pattern.matcher(str).matches());
    System.out.println(pattern.matcher(str2).matches());
    System.out.println(pattern.matcher(str3).matches());

  }
}
