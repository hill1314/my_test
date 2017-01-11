package com.hull.test.thread.pool;

import java.util.concurrent.*;

/**
 * Created by hull on 2017/1/10.
 */
public class ThreadPoolUtils {

    public static ExecutorService newThreadPool(){
        Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
        Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
        return Executors.newFixedThreadPool(10);    //创建固定容量大小的缓冲池
    }

}
