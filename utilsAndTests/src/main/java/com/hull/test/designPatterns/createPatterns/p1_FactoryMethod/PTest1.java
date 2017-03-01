package com.hull.test.designPatterns.createPatterns.p1_FactoryMethod;

/**
 * Created by hull on 2017/3/1.
 * 普通工厂模式，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 */
public class PTest1 {
    public static void main(String[] args) {
        Sender sender = SenderFactory.getSender("mail");
        sender.send("hello world!");

        Sender sender2 = SenderFactory2.getSmsSender();
        sender2.send("hello world!");
    }
}
