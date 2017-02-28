package com.hull.test.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hull on 2017/1/10.
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,   //corePoolSize：核心池的大小
                10,     //maximumPoolSize：线程池最大线程数
                200, TimeUnit.MILLISECONDS, //线程没有任务执行时最多保持多久时间会终止
                new ArrayBlockingQueue<Runnable>(6)); //workQueue：一个阻塞队列，用来存储等待执行的任务

        // 一个线程池 最多能同时添加的线程数为：maximumPoolSize + workQueue
        // 多了就报错了:java.util.concurrent.RejectedExecutionException
        for(int i=0;i<16;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println(
                    "线程池中线程数目："+executor.getPoolSize()
                    +",核心池大小:"+executor.getCorePoolSize()
                    +"，队列中等待执行的任务数目："+executor.getQueue().size()
                    +"，已执行完的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
