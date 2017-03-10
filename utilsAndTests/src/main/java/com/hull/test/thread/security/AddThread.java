package com.hull.test.thread.security;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/3/4.
 */
public class AddThread implements Runnable{
    private volatile int m = 0;
    ReentrantLock lock=new ReentrantLock();

    @Override
    public synchronized void run() {    //1、实现线程安全的第一中方法,加synchronized 关键字
        for (int i=0;i<5;i++){
//            lock.lock();  //2、实现线程安全的第二种方法
            int r = doit(m);
            this.m = r;
            System.out.println(Thread.currentThread().getName()+"--"+":"+r);
//            lock.unlock();

        }
    }

    public int doit(int m){
        return m+1;
    }
}
