package com.example.firstspringboot.common.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: sunwenwu
 * @Date: 2018/10/17 17：12
 * @Description:
 */
public class TestA {
    public String test(){
        return "annotation test。。。";
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println("before================="+list);

        for(int i=0;i<list.size();i++){
            list.remove(i);//元素删除不干净
        }
        System.out.println("after================="+list);

       /* for (Integer in:list){
            System.out.println(in);
            list.remove(in);//baocuo
        }*/



        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next();//先执行next，不然报错
            iterator.remove();

            if(list.size() == 5){
                break;
            }
        }
        System.out.println("after================="+list);
    }
}
