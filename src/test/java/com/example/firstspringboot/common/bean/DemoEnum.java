package com.example.firstspringboot.common.bean;

public enum DemoEnum {

    AA("11","aa"),
    BB("22","bb");

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
}
