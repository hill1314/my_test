package com.hull.spring.proxy.cglib;

/**
 * Created by Administrator on 2017/5/14.
 */
public class TestCGlibProxy {

    public static void main(String[] args) {

        try {
            //返回的是被代理类的子类引用
            SomeBody obj = (SomeBody) new GPCgiMeipo().getInstance(SomeBody.class);
            obj.findLove();//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
