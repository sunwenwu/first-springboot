package com.example.firstspringboot.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Author :sunwenwu
 * @Date : 2021/2/8 17:22
 * @Description :
 */
public class TestFun2 {

    private volatile Set<String> manualSingletonNames = new LinkedHashSet<>(16);

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);


    public static void main(String[] args) {
        TestFun2 tt = new TestFun2();

        System.out.println("manualSingletonNames before:"+JSON.toJSONString(tt.manualSingletonNames));
        System.out.println("beanDefinitionMap before"+JSON.toJSONString(tt.beanDefinitionMap));

        System.out.println("===================================");
        tt.registerSingleton("sss",new RootBeanDefinition());

        System.out.println("manualSingletonNames after:"+JSON.toJSONString(tt.manualSingletonNames));
        System.out.println("beanDefinitionMap after"+JSON.toJSONString(tt.beanDefinitionMap));
    }

    public void registerSingleton(String beanName, Object singletonObject) throws IllegalStateException {
        updateManualSingletonNames(set -> set.add(beanName), set -> {
            boolean a =  !this.beanDefinitionMap.containsKey(beanName);
            return a;
        });
    }

    private void updateManualSingletonNames(Consumer<Set<String>> action, Predicate<String> condition) {

        // Cannot modify startup-time collection elements anymore (for stable iteration)
        synchronized (this.beanDefinitionMap) {
            if (condition.test("s")) {
                Set<String> updatedSingletons = new LinkedHashSet<>(this.manualSingletonNames);
                action.accept(updatedSingletons);
                this.manualSingletonNames = updatedSingletons;
            }
        }
    }

}
