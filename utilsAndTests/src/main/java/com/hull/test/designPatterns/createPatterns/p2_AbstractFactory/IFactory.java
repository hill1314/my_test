package com.hull.test.designPatterns.createPatterns.p2_AbstractFactory;

import com.hull.test.designPatterns.createPatterns.p1_FactoryMethod.Sender;

/**
 * Created by hull on 2017/3/1.
 */
public interface IFactory {
    public Sender getSender();
}
