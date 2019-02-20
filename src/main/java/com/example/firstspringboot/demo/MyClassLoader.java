package com.example.firstspringboot.demo;

import java.io.*;

/**
 * @author: sunwenwu
 * @Date: 2019/1/7 17：14
 * @Description:
 */
public class MyClassLoader extends ClassLoader {

/*    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        System.out.println("zdy...");
        try{
            return findClass(name);
        }catch (ClassNotFoundException e){
      System.out.println("not found");
            return  super.loadClass(name);
        }
    }*/

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] raw = null;
        ByteArrayOutputStream out = null;

        String filename = "E:\\" + File.separatorChar +
                name.replace('.',File.separatorChar)+".class";
//        String filename = "/"+name.replace(".","/")+".class";
        InputStream in = null;
        try {
      //            in = MyClassLoader.class.getResourceAsStream(filename);
      in = new FileInputStream(filename);

            out=new ByteArrayOutputStream();

            byte[] buffer=new byte[2048];
            int len=0;
            while((len=in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            raw = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class<?> aClass = defineClass(name, raw, 0, raw.length);
        return aClass;
    }
}
