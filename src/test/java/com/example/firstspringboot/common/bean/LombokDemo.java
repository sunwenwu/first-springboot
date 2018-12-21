package com.example.firstspringboot.common.bean;

import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2018/12/18 16：20
 * @Description:
 */
@Data
public class LombokDemo {
    private String name;

    private Innertest innertest;

    class Innertest{
        private String age;
    }


    public static void main(String[] args) {
        LombokDemo lombokDemo = new LombokDemo();
    }
}
