package com.hull.test.camel.myTest;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by Administrator on 2017/1/1.
 */
public class MyRouterBuilder extends RouteBuilder {

    Processor1 p1 = new Processor1();
    Processor2 p2 = new Processor2();
    FileConvertProcessor processor = new FileConvertProcessor();

    @Override
    public void configure() throws Exception {
            from("file:f:/temp/inbox/?delay=30000")
            .process(processor)
//            .process(p1)
//            .process(p2)
            .to("file:f:/temp/outbox");
    }
}
