package com.hull.test.camel.myTest2;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultProducerTemplate;

/**
 * Created by hull on 2017/1/10.
 */
public class App {
    public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext(); // 1. 创建 CamelContext.
        ProducerTemplate template = new DefaultProducerTemplate(context);  // 创建 ProducerTemplate

        Object paramObj = new Object();
        Object result = template.requestBody("direct:"+"routerName",paramObj);


    }
}
