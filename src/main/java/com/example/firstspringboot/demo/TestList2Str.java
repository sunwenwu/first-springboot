package com.example.firstspringboot.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author :sunwenwu
 * @Date : 2021/1/26 15:12
 * @Description :
 */
public class TestList2Str {

    public static void main(String[] args) {

        System.out.println("结果："+StringUtils.join(Arrays.asList("1","3","9"), ","));
        System.out.println("结果："+StringUtils.join(Arrays.asList("2","0","1"), ","));
        System.out.println("结果："+StringUtils.join(new ArrayList<>(), ","));

        try {
            tt(new String[]{null});
        } catch (Exception e){

        }

        System.out.println("=======");

        System.out.println(System.identityHashCode("11"));
        System.out.println(System.identityHashCode("11"));
        System.out.println(System.identityHashCode("22"));
        System.out.println("11".hashCode());
        System.out.println("11".hashCode());
    }


    public static void tt(String... locations){
        Assert.noNullElements(locations, "Config locations must not be null");

    }
}
