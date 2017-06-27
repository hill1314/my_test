package com.hull.test.thread.methods;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/6/5.
 */
public class ThreadB extends Thread{

    @Override
    public void run() {
        for(int i=10;i>0;i--){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B count "+i);
        }
    }
}
