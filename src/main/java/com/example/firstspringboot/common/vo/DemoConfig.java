package com.example.firstspringboot.common.vo;

import org.springframework.context.annotation.Bean;

/**
 * @author: sunwenwu
 * @Date: 2018/10/23 17：10
 * @Description:
 */
public class DemoConfig {

    @Bean
    public DemoEvent get(){
        DemoEvent demoEvent = new DemoEvent("dd","1233","hh");
        return demoEvent;
    }
}
