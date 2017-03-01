package com.hull.test.designPatterns.createPatterns.p2_AbstractFactory;

import com.hull.test.designPatterns.createPatterns.p1_FactoryMethod.Sender;

/**
 * Created by hull on 2017/3/1.
 *  抽象工厂模式！！！
 工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，
 必须对工厂类进行修改，这违背了闭包原则，所以，从设计角度考虑，有一定的问题，如何解决？就用到抽象工厂模式，
 创建多个工厂类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码。

 一个实现 对应 一个自己的工厂
 */
public class PTest2 {
    public static void main(String[] args) {
        IFactory factory = new MSenderFactory();
        Sender sender = factory.getSender();
        sender.send("abstract factory!");
    }
}
