package com.example.firstspringboot;

import com.example.firstspringboot.common.vo.DemoConfig;
import com.example.firstspringboot.common.vo.DemoEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DemoConfig.class)
public class FirstspringbootApplication {

    public static void main(String[] args) {

//        SpringApplication.run(FirstspringbootApplication.class, args);
        SpringApplication application = new SpringApplication(FirstspringbootApplication.class);
        ConfigurableApplicationContext context = application.run(args);
        context.publishEvent(context.getBean(DemoEvent.class));
    }
}
