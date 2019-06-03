package com.example.firstspringboot.common.demo.threadPool;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author: sunwenwu
 * @Date: 2019/5/29 16：16
 * @Description:
 */
public class MyThreadTest {

    static Queue<String> queue = new ConcurrentLinkedQueue<String>();

    static {
        //入队列
        for (int i = 0; i < 9; i++) {
            queue.add("task" + i + "" + i);
        }
    }

    public static void main(String[] args) {
      ExecutorService executor = new ThreadPoolExecutor(5, 5,
              0L, TimeUnit.MILLISECONDS,
              new LinkedBlockingQueue<>());

      for (int i = 1;i<queue.size();i++) {

          executor.submit(new InnerThread());
      }

      executor.shutdown();
  }

}


/**
 * 线程：执行入队列、出队列任务的线程
 */
class InnerThread implements Runnable {

    /**
     * 线程安全的队列
     */

    @Override
    public void run() {


        //出队列

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "----- " + MyThreadTest.queue.poll());


    }

}