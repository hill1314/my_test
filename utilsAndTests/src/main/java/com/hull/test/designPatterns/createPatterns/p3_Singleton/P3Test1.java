package com.hull.test.designPatterns.createPatterns.p3_Singleton;

import com.hull.test.designPatterns.createPatterns.p3_Singleton.method1.Singleton1;
import com.hull.test.designPatterns.createPatterns.p3_Singleton.method2.Singleton2;

/**
 * Created by hull on 2017/3/1.
 */
public class P3Test1 {
    public static void main(String[] args) {
//        Singleton1 singleton1 = Singleton1.getInit();
        Singleton2 singleton2 = Singleton2.getInstance();
    }
}
