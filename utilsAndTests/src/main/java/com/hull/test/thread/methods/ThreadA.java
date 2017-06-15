package com.hull.test.thread.methods;


/**
 * Created by Administrator on 2017/6/5.
 */
public class ThreadA extends Thread{

    @Override
    public void run() {
        for(int i=10;i>0;i--){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A count "+i);
        }
    }
}
