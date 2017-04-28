package com.hull.test.designPatterns.structurePatterns.p7_decorator;

/**
 *
 * 装饰模式（Decorator）
 顾名思义，装饰模式就是给一个对象增加一些新的功能，而且是动态的，
 要求装饰对象和被装饰对象实现同一个接口，装饰对象持有被装饰对象的实例.
 Source类是被装饰类，Decorator类是一个装饰类，可以为Source类动态的添加一些功能

 * @author hull
 * @title
 * @description
 * @date 2017/4/28 16:56
 */
public class Decorator implements Sourceable{

    private Sourceable source;

    public Decorator(Sourceable source){
        super();
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }
}
