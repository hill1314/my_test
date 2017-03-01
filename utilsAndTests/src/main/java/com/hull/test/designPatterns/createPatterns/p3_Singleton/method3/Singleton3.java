package com.hull.test.designPatterns.createPatterns.p3_Singleton.method3;

/**
 * Created by hull on 2017/3/1.
 * 这个类可以满足基本要求，但是，像这样毫无线程安全保护的类，
 * 如果我们把它放入多线程的环境下，肯定就会出现问题了
 *
 * 因为我们只需要在创建类的时候进行同步，所以只要将创建和getInstance()分开，
 * 单独为创建加synchronized关键字，也是可以的
 */
public class Singleton3 {
    //定义一个静态类对象
    private static Singleton3 instance;

    //将构造方法私有
    private Singleton3(){
        System.out.println("Oh yes, u've got me!");
    }

    private static synchronized void syncInit(){
        instance = new Singleton3();
    }

    //公有的获取类对象实例的方法
    public static Singleton3 getInit(){
        if(instance==null){
            syncInit();
        }
        return instance;
    }
}
