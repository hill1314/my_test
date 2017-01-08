package com.hull.test.camel.test2;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Administrator on 2017/1/1.
 */
public class App2 {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.start();
        System.in.read();
    }
}
