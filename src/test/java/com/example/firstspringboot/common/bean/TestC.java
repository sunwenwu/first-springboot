package com.example.firstspringboot.common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: sunwenwu
 * @Date: 2018年10月19日18:16:33
 * @Description:
 */
public class TestC {
    public String test(){
        return " test。。annotation。";
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i+"");
        }
        list.add("6");
        list.add("7");
        list.add("8");

        System.out.println("before================="+list);
        System.out.println("before================="+list.get(0).getClass());

        List<Long> storeIdList = list.stream().map(caiv -> Long.valueOf(caiv)).collect(Collectors.toList());
        /*HashSet hs = new HashSet(storeIdList);
        storeIdList.clear();
        storeIdList.addAll(hs);*/
        storeIdList.stream().distinct().collect(Collectors.toList());

        System.out.println("after================="+storeIdList);
        System.out.println("after================="+storeIdList.get(0).getClass());


        System.out.println(String.join(System.lineSeparator(), list));


        int i= 1/0;

    }
}
