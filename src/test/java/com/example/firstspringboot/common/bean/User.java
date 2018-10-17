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

    public User(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }
}
