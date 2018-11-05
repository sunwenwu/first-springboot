package com.example.firstspringboot.common.bean;

import lombok.Data;

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

        InnerFanxingTest2<Number> test3 = new InnerFanxingTest2<>(11);
        InnerFanxingTest2<String> test4 = new InnerFanxingTest2<>("ss");

        show(test3);
        show(test4);
    }


    static void show(InnerFanxingTest2<?> obj){
        System.out.println(obj.getKey());

    }


    static class InnerFanxingTest implements Comparable,Serializable{

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
    @Data
    static class InnerFanxingTest2<U> implements Serializable{

       private U key;

        public InnerFanxingTest2(U key) {
            this.key = key;
        }
    }
}
