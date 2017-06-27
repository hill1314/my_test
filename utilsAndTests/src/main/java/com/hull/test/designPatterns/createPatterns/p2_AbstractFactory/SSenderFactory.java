package com.hull.test.designPatterns.createPatterns.p2_AbstractFactory;

import com.hull.test.designPatterns.createPatterns.p1_FactoryMethod.Sender;
import com.hull.test.designPatterns.createPatterns.p1_FactoryMethod.SmsSender;

/**
 * Created by hull on 2017/3/1.
 */
public class SSenderFactory implements IFactory{
    @Override
    public Sender getSender() {
        return new SmsSender();
    }
}
