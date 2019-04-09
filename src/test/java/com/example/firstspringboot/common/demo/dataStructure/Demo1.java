package com.example.firstspringboot.common.demo.dataStructure;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sunwenwu
 * @Date: 2019/2/21 14：59
 * @Description:
 */
public class Demo1 {

    public static final char[] chars = "((((((((([[[[[[[[[[[[[]]]]]]]))))))))".toCharArray();
//    public static final char[] chars = "()[]{}".toCharArray();

    public static final List<Character> newChars = new ArrayList<>();


    public static void main(String[] args) {

        for (int i=0; i<chars.length;i++) {
            char c = chars[i];
            if (FuhaoEnum.getSupportFuhaoChar().contains(c)) {
                push(c);
                System.out.println("push:---------------"+c);
            } else {
                if (CollectionUtils.isEmpty(newChars)) {
                    throw new RuntimeException("非法！");
                }
                System.out.println("pop:---------------"+c);
                if ( pop() == FuhaoEnum.getFuhaoEnumDescByTypeCode(c)) {
                    del();
                } else {
                    throw new RuntimeException("非法！");
                }
            }
        }

        if (!CollectionUtils.isEmpty(newChars)) {
            throw new RuntimeException("非法！");
        }

        System.out.println("合法");
    }

      static char pop() {
          return newChars.get(newChars.size()-1);
      }

      static void push(char a){
        newChars.add(a);
      }

      static void del(){
        newChars.remove(newChars.size()-1);
      }
}
