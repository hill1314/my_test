package com.hull.test.camel.test3;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by Administrator on 2017/1/2.
 */
public class HelloWorldRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8282/doHelloWorld")
                .process(new HttpProcessor())
                .to("log:helloworld?showExchangeId=true");
    }
}
