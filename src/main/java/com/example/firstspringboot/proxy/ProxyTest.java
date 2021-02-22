package com.example.firstspringboot.proxy;

import sun.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author :sunwenwu
 * @Date : 2021/2/19 17:05
 * @Description :
 */
public class ProxyTest {

    public static void main(String[] args) {

        Hello o = (Hello)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Hello.class}, new LogInvocationHandler(new HelloImpl()));
        o.say();
    }
}

class LogInvocationHandler implements InvocationHandler{

    private Hello hello;
    public LogInvocationHandler(Hello hello) {
        this.hello = hello;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class<?> callerClass = Reflection.getCallerClass(2);

        System.out.println(callerClass.getCanonicalName());

        System.out.println("proxy invoke...");
        return method.invoke(hello, args);
    }
}