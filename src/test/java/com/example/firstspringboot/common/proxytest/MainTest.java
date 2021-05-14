package com.example.firstspringboot.common.proxytest;

import java.lang.reflect.Proxy;

/**
 * @Author :sunwenwu
 * @Date : 2021/4/29
 * @Desc :
 */
public class MainTest {

    public static void main(String[] args) {

        BirdSerivce birdSerivce = new BirdSerivceImpl();
        
        BirdSerivce proxy = (BirdSerivce)Proxy.newProxyInstance(MainTest.class.getClassLoader(), new Class[]{BirdSerivce.class}, new MyTestInvocationHandler(birdSerivce));

        proxy.fly("张三");


    }
}
