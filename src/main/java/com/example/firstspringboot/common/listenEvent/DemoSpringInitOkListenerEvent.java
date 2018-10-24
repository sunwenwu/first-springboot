package com.example.firstspringboot.common.listenEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author: sunwenwu
 * @Date: 2018/10/24 09：54
 * @Description:
 */
public class DemoSpringInitOkListenerEvent implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("spring load finished..............");
    }
}
