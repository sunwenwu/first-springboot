package com.example.firstspringboot.common.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2018/10/17 17：43
 * @Description:
 */
@Data
public class User {
    @Excel(name = "用户名")
    private String userName;
    @Excel(name = "密码")
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
