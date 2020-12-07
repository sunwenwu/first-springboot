package com.example.firstspringboot.jdktest;

import lombok.Data;

import java.io.*;

/**
 * @Author :sunwenwu
 * @Date : 2020/11/26 11:12
 * @Description :
 */
public class TransientTest {

    @Data
    public static class Demo implements Serializable{

        private String name;

        //transient 表记不序列化
        private transient String sex;
    }


    public static void main(String[] args) throws Exception{

        Demo demo = new Demo();
        demo.setName("sww");
        demo.setSex("nan");
        System.out.println(demo);


        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("test"));

        outputStream.writeObject(demo);


        ObjectInputStream test = new ObjectInputStream(new FileInputStream("test"));

        Demo o = (Demo) test.readObject();

        System.out.println(o);
    }
}
