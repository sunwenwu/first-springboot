package com.example.firstspringboot.common.demo.other.test;

/**
 * @author: sunwenwu
 * @Date: 2018/10/24 10：23
 * @Description:
 */
public class StringComparable {

    public static void main(String[] args) {
        String s1= "abcd";
        String s2= "abcdaa";

        System.out.println(String.CASE_INSENSITIVE_ORDER.compare(s1,s2));

        System.out.println(s1.compareTo(s2));
    }
}
