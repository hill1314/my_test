package com.hull.test.thread.ticket;

/**
 * Created by Administrator on 2016/12/31.
 */
public class Timmer implements Runnable{
    private int num = 10;

    Timmer(){}

    Timmer(int num){
        this.num = num;
    }

    @Override
    public void run() {
        for (;num>0;num--){
             System.out.println(Thread.currentThread().getName()+":"+num);
        }
    }
}
