package com.hull.test.camel.myTest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Administrator on 2017/1/1.
 */
public class Processor1 implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("processor1....");
    }
}
