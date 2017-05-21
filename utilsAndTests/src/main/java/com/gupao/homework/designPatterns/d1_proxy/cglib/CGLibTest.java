package com.gupao.homework.designPatterns.d1_proxy.cglib;

/**
 * Created by Administrator on 2017/5/21.
 */
public class CGLibTest {
    public static void main(String[] args) {
        CGLibProxy proxy = new CGLibProxy();
        SomeOne someOne = (SomeOne) proxy.getInstance(SomeOne.class);
        someOne.doSomething();
    }
}
