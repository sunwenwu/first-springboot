package com.example.firstspringboot.demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author :sunwenwu
 * @Date : 2021/3/16
 * @Desc :
 */
public class ReentrantLockTest {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        System.out.println(lock.isLocked());

        lock.lock();
        lock.lock();

        System.out.println(lock.isLocked());

        lock.unlock();
        System.out.println("第一次解锁："+lock.isLocked());//true

        lock.unlock();
        System.out.println("第二次解锁："+lock.isLocked());//false

    }
}
