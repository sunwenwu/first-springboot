package com.example.firstspringboot.common.bean;

import java.io.Serializable;

/**
 * @author: sunwenwu
 * @Date: 2018/10/24 13：33
 * @Description:
 */
public class FanXingTest<T extends Comparable & Serializable> {

    private T aa;
    private T bb;

    public FanXingTest() {

    }

    public static void main(String[] args) {
        FanXingTest<String> test = new FanXingTest<>();
        FanXingTest<InnerFanxingTest> test2 = new FanXingTest<>();
    }


    static class InnerFanxingTest implements Comparable,Serializable{

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
}
