package com.example.firstspringboot.demo;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * @author: sunwenwu
 * @Date: 2019/4/11 11：17
 * @Description:
 */
public class TreadLocalTest {

    private static InheritableThreadLocal<Demo> threadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<Demo> threadLocalfu = new ThreadLocal<>();

    public static void main(String[] args) throws Exception{

//        sonTest();
        fuTest();


    }

    private static void fuTest() throws Exception{
        threadLocalfu.set(new Demo("父",10));




        Field field = TreadLocalTest.class.getDeclaredField("threadLocalfu");
        field.setAccessible(true);
        ThreadLocal o =(ThreadLocal) field.get(new TreadLocalTest());
    System.out.println("=====反射获取的======="+o.get());

        Thread t = new Thread(new SonThreadLocalTestFu((Demo)o.get()));

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("父打印：========"+threadLocalfu.get());
    }



    private static void sonTest() {
        threadLocal.set(new Demo("父",10));


        Thread t = new Thread(new SonThreadLocalTest(threadLocal));

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("父打印：========"+threadLocal.get());
    }
}


class SonThreadLocalTest implements Runnable {

    private InheritableThreadLocal<Demo> threadLocal;

    public SonThreadLocalTest(InheritableThreadLocal<Demo> threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        System.out.println("子打印：========"+threadLocal.get());
        threadLocal.get().setName("子修改父");

    }
}


class SonThreadLocalTestFu implements Runnable {

    public static ThreadLocal<Demo> threadLocalaa = new ThreadLocal<>();

    private Demo demo;

    public SonThreadLocalTestFu(Demo e) {
        this.demo = e;
    }

    @Override
    public void run() {
        threadLocalaa.set(demo);
        System.out.println("子打印：========"+threadLocalaa.get());
        threadLocalaa.get().setName("子修改父");

    }
}


@Data
class Demo {
    private String name;
    private Integer age;

    public Demo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}