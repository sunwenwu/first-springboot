package com.example.firstspringboot.proxy;

/**
 * @Author :sunwenwu
 * @Date : 2021/2/19 17:08
 * @Description :
 */
public class HelloImpl implements Hello{

    @Override
    public String say() {
        System.out.println("say hello..");
        return "say";
    }
}
