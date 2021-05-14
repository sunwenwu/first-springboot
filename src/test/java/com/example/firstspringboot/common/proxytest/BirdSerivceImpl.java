package com.example.firstspringboot.common.proxytest;

/**
 * @Author :sunwenwu
 * @Date : 2021/4/29
 * @Desc :
 */
public class BirdSerivceImpl implements BirdSerivce{

    @Override
    public void fly(String name){
        System.out.println(name + "-->   飞");
    }
}
