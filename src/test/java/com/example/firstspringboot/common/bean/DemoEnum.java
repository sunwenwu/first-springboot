package com.example.firstspringboot.common.bean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DemoEnum {

    AA("11","aa"),
    BB("22","bb"),
    CC("33","cc");

    private String code;
    private String name;

    DemoEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public void test(){
        System.out.println(this.getCode()+":"+this.getName());
        System.out.println(this.getCode()+":"+this.getName());
        System.out.println(this.getCode()+":"+this.getName());
        System.out.println(this.getCode()+":"+this.getName());
        System.out.println(this.getCode()+":"+this.getName());
        System.out.println(this.getCode()+":"+this.getName());
        System.out.println(this.getCode()+":"+this.getName());
        System.out.println(this.getCode()+":-----"+this.getName());
    }


    public static void main(String[] args) {
        DemoEnum[] enumConstants = DemoEnum.class.getEnumConstants();

        for(DemoEnum demoEnum : enumConstants){

            System.out.println(demoEnum.getCode() +" :"+demoEnum.getName());
        }
        List<String> collect = Arrays.stream(enumConstants).map(demoEnum -> {
            return demoEnum.getCode();
        }).collect(Collectors.toList());

        System.out.println(collect);
    }
}
