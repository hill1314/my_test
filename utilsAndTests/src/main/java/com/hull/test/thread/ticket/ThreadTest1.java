package com.hull.test.thread.ticket;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/31.
 * 多线程测试
 */
public class ThreadTest1 {
    private static ArrayList arrays = new ArrayList();

    public static void initArray(){
        for(int i=1;i<=100;i++){
            arrays.add(i);
        }
    }

//    public static void thread1(){
//        initArray();
//        MyThread t1 = new MyThread();
//        MyThread t2 = new MyThread();
//        t1.start();
//        t2.start();
//    }

    public static void thread2(){
        Timmer timmer = new Timmer(100);
        Thread t1 = new Thread(timmer,"A");
        Thread t2 = new Thread(timmer,"B");

        t1.start();
        t2.start();
    }

    public static void thread3(){
        Tickets tickets = new Tickets();
        Thread t1 = new Thread(tickets,"A");
        Thread t2 = new Thread(tickets,"B");

        t1.start();
        t2.start();
    }



    public static void main(String[] args){
        long begin = new Date().getTime();
        thread3();


        long end = new Date().getTime();
        System.out.println(end-begin);
    }

}
