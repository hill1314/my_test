package com.hull.test.thread.methods;

/**
 * Created by Administrator on 2017/6/5.
 */
public class JoinTest {
    public static void main(String[] args) throws Exception {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.join();
        threadB.start();
    }

}
