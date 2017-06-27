package com.hull.test.camel.test2;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/1.
 */
public class MyProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getOut().setBody(new Date().toString());
    }
}
