package com.example.firstspringboot.common.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author :sunwenwu
 * @Date : 2021/4/29
 * @Desc :
 */
public class MyTestInvocationHandler implements InvocationHandler {

    private BirdSerivce birdSerivce;

    public MyTestInvocationHandler(BirdSerivce birdSerivce){
        this.birdSerivce = birdSerivce;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("==========开始飞");
        Object invoke = method.invoke(birdSerivce, args);
        System.out.println("==========结束飞");

        return invoke;
    }
}
