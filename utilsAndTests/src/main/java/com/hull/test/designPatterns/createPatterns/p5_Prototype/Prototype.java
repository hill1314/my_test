package com.hull.test.designPatterns.createPatterns.p5_Prototype;

/**
 * Created by hull on 2017/3/1.
 */
public class Prototype implements Cloneable {

    public Object clone() throws CloneNotSupportedException {
        Prototype proto = (Prototype) super.clone();
        return proto;
    }
}
