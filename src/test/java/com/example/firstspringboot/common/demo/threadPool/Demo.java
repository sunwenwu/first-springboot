package com.example.firstspringboot.common.demo.threadPool;

import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/** @author: sunwenwu @Date: 2019/6/19 17：37 @Description: */
public class Demo {

  public static TaskExecutor taskOpenOrderExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    // 配置核心线程数
    taskExecutor.setCorePoolSize(5 / 2);
    // 配置最大线程数
    taskExecutor.setMaxPoolSize(10);
    // 配置队列大小
    taskExecutor.setQueueCapacity(10);
    // 配置线程池中的线程的名称前缀
    taskExecutor.setThreadNamePrefix("ka-open-order-thread-");
    taskExecutor.setKeepAliveSeconds(10000);
    taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    taskExecutor.initialize();
    return taskExecutor;
  }

  public static void main(String[] args) throws Exception{

      Boolean flag = true;

      CountDownLatch countDownLatch = new CountDownLatch(2);

      System.out.println("主："+flag);

      System.out.println("主："+countDownLatch.toString());

      taskOpenOrderExecutor().execute( () -> {
          singleOpenOrder(flag,countDownLatch);
      });

      countDownLatch.await();

      //
  }

  private static void singleOpenOrder(Boolean flag, CountDownLatch countDownLatch) {

    System.out.println("子："+flag);
    flag = false;

    System.out.println("子："+countDownLatch.toString());

      countDownLatch.countDown();
  }
}