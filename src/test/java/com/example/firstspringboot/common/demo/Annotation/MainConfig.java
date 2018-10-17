package com.example.firstspringboot.common.demo.Annotation;

import com.example.firstspringboot.common.bean.TestA;
import com.example.firstspringboot.common.bean.TestB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: sunwenwu
 * @Date: 2018/10/17 17：13
 * @Description:
 */
@Import({TestA.class, TestB.class})
@Configuration
public class MainConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanNames = context.getBeanDefinitionNames();
        for(int i=0;i<beanNames.length;i++){
            System.out.println("bean名称为==="+beanNames[i]);
        }

        TestA bean = context.getBean(TestA.class);
        System.out.println(bean.test());

    }
}
