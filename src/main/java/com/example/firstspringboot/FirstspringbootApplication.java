package com.example.firstspringboot;

import com.example.firstspringboot.common.listenEvent.DemoSpringInitOkListenerEvent;
import com.example.firstspringboot.common.vo.DemoConfig;
import com.example.firstspringboot.common.vo.DemoEvent;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
//@Import({DemoConfig.class, DemoSpringInitOkListenerEvent.class})
//@MapperScan(value = "com.example.firstspringboot.dao")
public class FirstspringbootApplication {

    public static void main(String[] args) {

        SpringApplication.run(FirstspringbootApplication.class, args);
        /*SpringApplication application = new SpringApplication(FirstspringbootApplication.class);
        ConfigurableApplicationContext context = application.run(args);
        context.publishEvent(context.getBean(DemoEvent.class));*/
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter(){
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                filterChain.doFilter(request, response);
            }
        };
    }
}
