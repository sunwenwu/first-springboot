package com.example.firstspringboot.demo;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author: sunwenwu
 * @Date: 2019/1/10 10：43
 * @Description:
 */
public class HighOrderFunctions {

    private static List<Integer> nums;
    private static int initCount = 100;
    static {

        nums = new ArrayList<>(initCount);
        for (int i =1 ; i<=initCount; i++) {
            nums.add(i);
        }
    }

    private static <T> Predicate<T> notEqual(T t) {
        return (a) -> !Objects.equals(a, t);
    }

    public static void main(String[] args) throws Exception{

       /* Arrays.asList(1,2,3)
                .stream()
                .filter(notEqual(1))
                .forEach(System.out::println);*/

       run();
//       run2();

        HighOrderFunctions functions = new HighOrderFunctions();
//        functions.run3();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    System.out.println(sdf.format(new Date()));
    }






    public static void run () {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6,7,8,9,10);

        List<Integer> ins = new ArrayList<>();
        int num = 0;
        for (int i :integers) {
            ins.add(i);
            num++;

            if (num % 4 == 0 || num == integers.size()) {
                System.out.println(ins);
                ins.clear();
            }
        }
    }
    public static void run4 () {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6,7,8,9);

        List<Integer> ins = new ArrayList<>();
        int num = 0;
        for (int i :integers) {
            ins.add(i);
            num++;

            if (num % 4 == 0 || integers.size()-num < 4) {
                System.out.println(ins);
                ins.clear();
            }
        }
    }

    /**
     * run2 ========执行时间======151
     */
    public static void run2 () {
        long start = System.currentTimeMillis();
//        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6,7,8,9);
        List<Integer> integers = nums;


        List<Integer> ins = new ArrayList<>();
        int num = 0;
        for (int i :integers) {
            ins.add(i);
            num++;

            if (num % 4 == 0) {
                System.out.println(ins);
                ins.clear();
            }
        }
        if (integers.size() % 4 != 0) {
            System.out.println(ins);
        }
        long end = System.currentTimeMillis();
        System.out.println("run2 ========执行时间======"+(end -start));
    }

    public  void run3 () throws Exception{
//        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6,7,8,9);
        List<Integer> integers = nums;

        ForkJoinPool pool=new ForkJoinPool();
        MyTask task= new MyTask(integers);
    System.out.println("before:"+pool.getPoolSize());
        pool.submit(task);

        System.out.println("run3 ========执行时间======"+task.get());
        System.out.println("after:"+pool.getPoolSize());
//        pool.awaitTermination(20, TimeUnit.SECONDS);//等待20s，观察结果
//        pool.shutdown();

    }


    class MyTask extends RecursiveTask<Long> {

        private List<Integer> tasks;

        int THRESHOLD = 1000;

        MyTask (List<Integer> tasks) {
            this.tasks = tasks;
        }

        @Override
        protected Long compute() {

            long start = System.currentTimeMillis();

            if (tasks.size() <= THRESHOLD) {
                System.out.println(Thread.currentThread().getName()+"==================================="+tasks);
            } else {
                List<Integer> left = tasks.subList(0, THRESHOLD);
                List<Integer> right = tasks.subList(THRESHOLD, tasks.size());

                MyTask leftTask = new MyTask(left);
                MyTask rightTask = new MyTask(right);

                leftTask.fork();
                rightTask.fork();

                return leftTask.join() + rightTask.join();
            }
            long end = System.currentTimeMillis();

            return end-start;

        }
    }
}
