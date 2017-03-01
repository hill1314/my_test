package com.hull.test.designPatterns.createPatterns.p3_Singleton.method1;

/**
 * Created by hull on 2017/3/1.
 * 这个类可以满足基本要求，但是，像这样毫无线程安全保护的类，
 * 如果我们把它放入多线程的环境下，肯定就会出现问题了
 */
public class Singleton1 {
    //定义一个静态类对象
    private static Singleton1 singleton1;

    //将构造方法私有
    private Singleton1(){
        System.out.println("Oh yes, u've got me!");
    }

    //公有的获取类对象实例的方法
    public static Singleton1 getInit(){
        if(singleton1==null){
            singleton1 = new Singleton1();
        }
        return singleton1;
    }
}
