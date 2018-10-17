package com.example.firstspringboot.common.demo.Annotation;

import com.example.firstspringboot.common.bean.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: sunwenwu
 * @Date: 2018/10/17 17：13
 * @Description:
 */
@Configuration
public class MainConfig_2 {
    @Bean
    public User get(){
        System.out.println("user init before....");
        return new User("sww","123");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig_2.class);

        User bean = context.getBean(User.class);
        System.out.println("@Bean test---:------"+bean);

    }
}
