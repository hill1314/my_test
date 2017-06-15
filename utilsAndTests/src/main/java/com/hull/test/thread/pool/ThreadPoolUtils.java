package com.hull.test.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hull on 2017/1/10.
 *  参考：http://blog.csdn.net/bairrfhoinn/article/details/16848785
 *
 *  ExecutorService 只是壹個接口，你壹量需要使用它，那麽就需要提供壹個该接口的实现。
 *  ExecutorService 接口在 java.util.concurrent 包中有如下实现类：
        ThreadPoolExecutor
        ScheduledThreadPoolExecutor
 */
public class ThreadPoolUtils {

//    public static ExecutorService newThreadPool(){
//        //可以创建三种类型的线程池
//        Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
//        Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
//        return Executors.newFixedThreadPool(10);    //创建固定容量大小的缓冲池
//    }

//    有几种不同的方式让你将任务委托给壹個 ExecutorService
//    execute(Runnable)
//    submit(Runnable)
//    submit(Callable)
//    invokeAny(...)
//    invokeAll(...)

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 接收一個 java.lang.Runnable 对象作为参数，并且以异步的方式执行它
//        executorService.execute(new MyTask(111));
        //同样接收壹個 Runnable 的实现作为参数，但是会返回壹個 Future 对象。
        // 这個 Future 对象可以用于判断 Runnable 是否结束执行
//        Future future = executorService.submit(new MyTask(111));

        List<TcSupLoanInfo> allList = new ArrayList<>();
        for(int n=0;n<1000;n++){
            allList.add(new TcSupLoanInfo(n+""));
        }

        int step = allList.size()/10;
        List<TcSupLoanInfo> subList = new ArrayList<>();

        Long begTime = System.currentTimeMillis();

        for(int i=0;i<10;i++){
            int begx = i*step;
            int endx = begx + step;
            if(i==9){
                endx = allList.size();
            }
            subList = allList.subList(begx,endx);
            executorService.execute(new MyTask2(subList));
        }

        Long endTime = System.currentTimeMillis();


        System.out.println("耗时："+(endTime-begTime)+"ms");

//        executorService.shutdown(); //不再接受新的任务，等线程执行完后停止
//        executorService.shutdownNow();//立即停止
    }
}
