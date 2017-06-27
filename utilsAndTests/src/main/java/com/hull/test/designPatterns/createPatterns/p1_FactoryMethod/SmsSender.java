package com.hull.test.designPatterns.createPatterns.p1_FactoryMethod;

/**
 * Created by hull on 2017/3/1.
 */
public class SmsSender implements Sender{
    @Override
    public void send(String msg) {
        System.out.println("sms send:"+msg);
    }
}
