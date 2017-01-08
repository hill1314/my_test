package com.hull.test.camel.test2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/1/1.
 *
 *  TODO  redis 还不熟，晚点再研究 用例3和4
 *
 */
public class App3 {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("camel-context.xml");
        context.start();
        System.in.read();
    }
}
