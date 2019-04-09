package com.example.firstspringboot.demo;


import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author: sunwenwu
 * @Date: 2019/1/7 16：58
 * @Description:
 */
public class ClassLoaderTestEquals {

  public static void main(String[] args) throws Exception {
    ClassLoader myclassLoader = new ClassLoader() {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                String s = name.substring(name.lastIndexOf(".") + 1) + ".class";

                InputStream in = getClass().getResourceAsStream(s);

                if (in == null) {
                    return super.loadClass(name);
                }
                byte[] bytes = new byte[in.available()];



                in.read(bytes);

                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                throw new ClassNotFoundException(name);
            }
        }
    };

      Class<?> aClass = myclassLoader.loadClass("com.example.firstspringboot.demo.ClassLoaderTestEquals");

      Object o = aClass.newInstance();

    System.out.println(o.getClass().getClassLoader());

    System.out.println(o instanceof com.example.firstspringboot.demo.ClassLoaderTestEquals);
  }



}
