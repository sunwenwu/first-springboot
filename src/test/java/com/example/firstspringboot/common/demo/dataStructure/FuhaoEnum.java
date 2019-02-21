package com.example.firstspringboot.common.demo.dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: sunwenwu
 * @Date: 2019/2/21 15：20
 * @Description:
 */
public enum FuhaoEnum {

    A('(',')'),
    B('[',']'),
    C('{','}');

   private char zheng;

   private char fan;

   FuhaoEnum(char zheng, char fan) {
        this.zheng = zheng;
        this.fan = fan;
    }

    public char getZheng() {
        return zheng;
    }

    public char getFan() {
        return fan;
    }


    public static Character getFuhaoEnumDescByTypeCode(Character typeCode) {
        if (null != typeCode) {
            for (FuhaoEnum payTypeEnum : FuhaoEnum.values()) {
                if (typeCode.equals(payTypeEnum.getFan())) {
                    return payTypeEnum.getZheng();
                }
            }
        }
        return null;
    }

   /* public static List<Character> getSupportFuhaoChar() {
        List<Character> list = new ArrayList<>();

        for (FuhaoEnum value : FuhaoEnum.values()) {
            list.add(value.getZheng());
        }

        return list;
    }*/

    private static List<Character> supportFuhaoChars = null;

    public static List<Character> getSupportFuhaoChar() {
        if (supportFuhaoChars == null) {
            synchronized(FuhaoEnum.class){
                if (supportFuhaoChars == null) {
                    supportFuhaoChars = Arrays.stream(FuhaoEnum.class.getEnumConstants()).map(fuhaoEnum -> {
                        return fuhaoEnum.getZheng();
                    }).collect(Collectors.toList());
                }
            }
        }
        return supportFuhaoChars;
    }


}
