package com.example.firstspringboot.common.bean;

import java.io.Serializable;

/**
 * @author: sunwenwu
 * @Date: 2018/10/24 13：33
 * @Description:
 */
public class FanXingTest2<T extends Comparable & Serializable> {

    private T aa;
    private T bb;

    public FanXingTest2() {

    }

    public static void main(String[] args) throws Exception{
        FanXingTest2<String> test = new FanXingTest2<>();
        FanXingTest2<Integer> test2 = new FanXingTest2<>();
        Class<? extends FanXingTest2> aClass = test2.getClass();

        boolean b = test instanceof FanXingTest2;

        System.out.println();
        System.out.println(test.getClass() == test2.getClass());
    }



}
