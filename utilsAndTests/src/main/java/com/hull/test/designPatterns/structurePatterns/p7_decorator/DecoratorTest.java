package com.hull.test.designPatterns.structurePatterns.p7_decorator;

/**
 * @author hull
 * @title
 * @description
 * @date 2017/4/28 17:02
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable decorator = new Decorator(source);

        decorator.method();

    }
}
