package com.hull.test.designPatterns.structurePatterns.p7_decorator;

/**
 * @author hull
 * @title
 * @description
 * @date 2017/4/28 16:55
 */
public class Source implements Sourceable{
    @Override
    public void method() {
        System.out.println("do something...");
    }
}
