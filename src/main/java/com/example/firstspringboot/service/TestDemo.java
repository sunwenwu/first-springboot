package com.example.firstspringboot.service;

import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2019/3/27 17：24
 * @Description:
 */
@Data
public class TestDemo {

    private TestService testService;

    public  void run () {
        testService.run();
    }
}
