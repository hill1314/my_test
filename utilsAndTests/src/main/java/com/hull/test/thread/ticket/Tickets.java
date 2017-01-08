package com.hull.test.thread.ticket;

/**
 * Created by Administrator on 2017/1/1.
 */
public class Tickets implements Runnable{
    private Foo foo = new Foo();
    private int num ;

    @Override
    public void run() {
       num = foo.getX();
        for (;num>0;){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            foo.fix(1);
            num = foo.getX();
            System.out.println(Thread.currentThread().getName()+": 剩余票数："+num);
        }
    }
}
