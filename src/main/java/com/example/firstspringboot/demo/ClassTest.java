package com.example.firstspringboot.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author: sunwenwu
 * @Date: 2019/1/2 17：54
 * @Description:
 */
public class ClassTest {

  public static void main(String[] args) throws IOException {

      InputStream resourceAsStream3 = ClassTest.class.getResourceAsStream("/com/example/firstspringboot/demo/Test.class");
      InputStream resourceAsStream2 = ClassTest.class.getResourceAsStream("/com/example/firstspringboot/demo/Test.class");
      InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/example/firstspringboot/demo/Test.class");
      BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));

      String s = null;
      while ((s = br.readLine()) != null){
        System.out.println(s);
      }


      ClassLoader cl = ClassTest.class.getClassLoader();

      while (cl != null) {
          System.out.println("before:"+cl);

          cl = cl.getParent();

          System.out.println("after:"+cl);

      }

      URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
      for (URL url : urLs) {
          System.out.println(url.toExternalForm());
      }

    System.out.println(System.getProperty("java.class.path"));
    System.out.println(String.class.getClassLoader());
  }
}
