package com.example.firstspringboot.common.bean;

import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2018/10/17 17：43
 * @Description:
 */
@Data
public class User {
    private String userName;
    private String pwd;

    public User(String s, String s1) {
        this.userName=s;
        this.pwd=s1;
    }


    public static void main(String[] args) {
        User u = new User("33","44");
        System.out.println(u.getPwd()+u.getUserName());
    }
}
