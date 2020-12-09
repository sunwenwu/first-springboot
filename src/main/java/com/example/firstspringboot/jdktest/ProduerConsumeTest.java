package com.example.firstspringboot.jdktest;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author :sunwenwu
 * @Date : 2020/12/7 14:52
 * @Description :
 */
public class ProduerConsumeTest {

    @Data
    public static class Tong{

        private Integer maxLimit = 10;

        private List<Integer> list = new ArrayList<>();

        public synchronized void put (Integer value) {
            if (isFull()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.add(value);
            System.out.println("put------>"+value);

            this.notifyAll();
        }

        public synchronized Integer take(){

            if (isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer value = list.remove(0);
            System.out.println("take------------------>"+value);

            this.notifyAll();
            return value;
        }

        public boolean isFull () {
            return list.size() >= maxLimit ? true : false;
        }

        public boolean isEmpty (){
            return list.isEmpty();
        }
    }


    public static void main(String[] args) throws Exception{

        TongNew tong = new TongNew();
//        Tong tong = new Tong();
        final AtomicInteger count = new AtomicInteger(0);

        final Integer size = 1000;

        CountDownLatch countDownLatchPut = new CountDownLatch(size);
        CountDownLatch countDownLatchTake = new CountDownLatch(size);

        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    tong.put(count.incrementAndGet());
                    countDownLatchPut.countDown();
                }

            });
            t.start();
        }

        for (int i = 0; i < size; i++) {
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {

                   tong.take();
                   countDownLatchTake.countDown();
                }

            });
            t.start();

        }

        countDownLatchPut.await();
        countDownLatchTake.await();

        System.out.println("耗时："+(System.currentTimeMillis()-start));
    }




    @Data
    public static class TongNew{

        private Integer maxLimit = 10;

        private List<Integer> list = new ArrayList<>();

        private ReentrantLock lock = new ReentrantLock();

        private Condition isFull = lock.newCondition();

        private Condition isEmpty = lock.newCondition();

        public  void put (Integer value) {
            lock.lock();
            while  (isFull()) {
                try {
                    isFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.add(value);
            System.out.println("put------>"+value);

            isEmpty.signal();
            lock.unlock();
        }

        public  Integer take(){
            lock.lock();
            while  (isEmpty()) {
                try {
                    isEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer value = list.remove(0);
            System.out.println("take------------------>"+value);

            isFull.signal();
            lock.unlock();
            return value;
        }

        public boolean isFull () {
            return list.size() >= maxLimit ? true : false;
        }

        public boolean isEmpty (){
            return list.isEmpty();
        }
    }
}
