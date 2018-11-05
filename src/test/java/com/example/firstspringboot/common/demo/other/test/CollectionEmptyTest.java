package com.example.firstspringboot.common.demo.other.test;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author: sunwenwu
 * @Date: 2018/10/25 14：40
 * @Description:
 */
public class CollectionEmptyTest {
    public static void main(String[] args) {
        List list = null;
        System.out.println(CollectionUtils.isEmpty(list));
        list =  new ArrayList();
        System.out.println(CollectionUtils.isEmpty(list));
        System.out.println(list.isEmpty());

        list.add(1);
        System.out.println(CollectionUtils.isEmpty(list));

    }
}
