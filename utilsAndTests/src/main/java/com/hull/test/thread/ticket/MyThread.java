package com.hull.test.thread.ticket;

/**
 * Created by Administrator on 2016/12/31.
 */
public class MyThread extends Thread{
    private int num=0;

    MyThread(){}
    MyThread(int num){
        this.num = num;
    }

    @Override
    public void run(){
        for (num=0;num<100;num++){
            System.out.println(Thread.currentThread().getName()+":"+num);
        }
    }


}
