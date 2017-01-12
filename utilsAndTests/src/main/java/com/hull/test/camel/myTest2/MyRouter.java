package com.hull.test.camel.myTest2;

import org.apache.camel.spring.SpringRouteBuilder;

/**
 * Created by hull on 2017/1/10.
 */
public class MyRouter extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:d:/temp/inbox/?delay=30000")
//                .bean(p1)
//                .bean(p2)
                .to("file:d:/temp/outbox");
    }
}
