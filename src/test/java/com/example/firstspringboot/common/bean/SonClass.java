package com.example.firstspringboot.common.bean;

/**
 * @author: sunwenwu
 * @Date: 2018/10/25 10：38
 * @Description:
 */
public class SonClass extends FatherClass {

    public void test2(){
        System.out.println("S....");
    }

    public static void main(String[] args) {
        FatherClass f = new SonClass();
        f.test();
    }
}
