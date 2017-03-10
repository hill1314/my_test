package com.hull.test.thread.security;

/**
 * Created by Administrator on 2017/3/4.
 */
public class MTest {
    public static void main(String[] args) {
        AddThread add = new AddThread();
        for(int i =0;i<1000;i++){
            new Thread(add).start();
        }
    }
}
