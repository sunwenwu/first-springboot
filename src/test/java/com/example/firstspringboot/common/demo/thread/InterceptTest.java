package com.example.firstspringboot.common.demo.thread;

/**
 * @Author :sunwenwu
 * @Date : 2020/10/21 14:04
 * @Description :
 */
public class InterceptTest {


    public static void main(String[] args) throws Exception{
        DemoThread demoThread = new DemoThread();

        System.out.println("1:"+demoThread.getState());

        demoThread.start();
        System.out.println("2:"+demoThread.getState());

        System.out.println("主1："+demoThread.isInterrupted());

        Thread.sleep(5* 1000);
        demoThread.interrupt();
        System.out.println("3:"+demoThread.getState());


        System.out.println("主2："+demoThread.isInterrupted());

    }
}


class DemoThread extends Thread{

    public static volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("demoThread begin.....");

        while (flag) {

          /*  if (Thread.currentThread().isInterrupted()) {
                System.out.println("interrupted...ok");
            }*/

            if (Thread.currentThread().interrupted()) {
                System.out.println("interrupted...ok");
                System.out.println(Thread.currentThread().getState());
            }

            /*try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                System.out.println("catch InterruptedException...");
                System.out.println("从1："+this.isInterrupted());

            }*/

        }
        System.out.println("demoThread finish.....");

    }
}