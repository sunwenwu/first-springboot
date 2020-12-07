package com.example.firstspringboot.common.demo.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author :sunwenwu
 * @Date : 2020/10/21 16:40
 * @Description :
 */
public class Test2 {

    public static void main(String[] args) throws Exception{
        ReentrantLock lock = new ReentrantLock();


        Thread tt1 =  new Thread(()->{
            try {

                System.out.println("tt1 开始获取锁");

                lock.lock();
                System.out.println("tt1 成功获取锁");
                Thread.sleep(10 * 1000);

            } catch (InterruptedException e) {
                System.out.println("tt1 被打断");
                e.printStackTrace();
            }
        });

        Thread tt2 =  new Thread(()->{
            try {
                System.out.println("tt2 开始获取锁");
                lock.tryLock(10, TimeUnit.SECONDS);
                System.out.println("tt12 成功获取锁");

            } catch (InterruptedException e) {
                System.out.println("tt2 被打断");

                e.printStackTrace();
            }
        });

        tt1.start();

        Thread.sleep(2 * 1000);

        tt2.start();
        Thread.sleep(2 * 1000);

        tt2.interrupt();
    }
}
