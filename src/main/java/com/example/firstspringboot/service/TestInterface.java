package com.example.firstspringboot.service;

import java.lang.reflect.Method;

/**
 * @Author :sunwenwu
 * @Date : 2020/12/23 14:35
 * @Description :
 */
public class TestInterface {

    public static void main(String[] args) throws Exception{
        UserService userService = new UserServiceImpl();

        Method run = null;

        try {
            run = UserService.class.getMethod("run1",String.class);
            run.invoke(userService,"66");

        } catch (NoSuchMethodException e){
            System.out.println("方法没找到");
        }


        System.out.println("结束。。。");

    }
}
