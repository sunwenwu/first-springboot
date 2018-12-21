package com.example.firstspringboot.common.demo.other.test;

import com.example.firstspringboot.common.utils.MD5Tool;

/**
 * @author: sunwenwu
 * @Date: 2018/12/3 15：50
 * @Description:
 */
public class PwdTest {

    public static void main(String[] args) {
        System.out.println(MD5Tool.toByteArray("Y8/DuIS+fVgR/f39AROaX3dSIIo="));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);


        System.out.println("".length());
        System.out.println("一".length());
        System.out.println("一二".length());
        System.out.println("一二三".length());
        System.out.println("1".length());
        System.out.println("12".length());
        System.out.println("123".length());
        System.out.println("a".length());
        System.out.println("ab".length());
        System.out.println("abc".length());


        System.out.println(String.format("调拨单:%s,Dlink审核回调-同步库存失败：%s","5656454511"," null error"));
    }
}
