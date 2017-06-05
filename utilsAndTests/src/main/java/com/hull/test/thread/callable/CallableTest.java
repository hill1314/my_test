package com.hull.test.thread.callable;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/6/5.
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future =  executorService.submit(new MyCallable());
        System.out.println(future.get());
    }

}
