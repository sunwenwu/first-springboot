package com.example.firstspringboot.common.utils;

/**
 * @Author :sunwenwu
 * @Date : 2019/10/22 16:25
 * @Description :
 */
public class SubStringTest {


    public static void main(String[] args) {
        String a = "0123456789";
        System.out.println(a+"========="+a.length());

        if (a.length()>9) {
            System.out.println("....");
            a = a.substring(0,9);
        }

        System.out.println(a+"========="+a.length());
    }
}
