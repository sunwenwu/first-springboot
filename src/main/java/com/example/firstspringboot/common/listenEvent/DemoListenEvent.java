package com.example.firstspringboot.common.listenEvent;

import com.example.firstspringboot.common.vo.DemoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: sunwenwu
 * @Date: 2018/10/23 17：00
 * @Description:
 */
@Component
public class DemoListenEvent implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println(String.format("DemoEvent test...{%s},{%s}",demoEvent.getCode(),demoEvent.getMsg()));
    }
}
