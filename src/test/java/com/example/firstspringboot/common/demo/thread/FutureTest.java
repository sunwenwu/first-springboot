package com.example.firstspringboot.common.demo.thread;

import java.util.concurrent.*;

/**
 * @Author :sunwenwu
 * @Date : 2020/10/21 14:42
 * @Description :
 */
public class FutureTest {

    public static void main(String[] args) throws Exception{
        Thread futureThread = null;
        String executeResult = "no";
        try {
            FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(10 * 1000);
                    return "ok";
                }
            });
            futureThread = new Thread(futureTask);
            futureThread.start();

            executeResult = futureTask.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {

            executeResult = "job execute timeout ";
        } catch (InterruptedException e) {
            System.out.println("打断异常");
        } catch (ExecutionException e) {
            System.out.println("执行异常。。。");
        } finally {
            System.out.println("打断。。。");
            futureThread.interrupt();
        }

        System.out.println(futureThread.getState());
        while (true) {
            int i = 1;
            Thread.State state = futureThread.getState();
            System.out.println("--------"+i+"-----------:"+futureThread.getState());

            if ("TERMINATED".equals(state.toString())) {
                break;
            }
            Thread.sleep(1 * 1000);
            i++;

        }



        System.out.println(executeResult);
    }
}
