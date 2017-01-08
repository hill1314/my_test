package com.hull.test.camel.test3;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;

/**
 * Created by Administrator on 2017/1/2.
 */
public class App {
    public static void main(String[] args) throws Exception {
        // 这是camel上下文对象，整个路由的驱动全靠它了。
        ModelCamelContext camelContext = new DefaultCamelContext();
        // 启动route
        camelContext.start();
        // 将我们编排的一个完整消息路由过程，加入到上下文中
        camelContext.addRoutes(new HelloWorldRouter());

        /*
         * ==========================
         * 为什么我们先启动一个Camel服务
         * 再使用addRoutes添加编排好的路由呢？
         * 这是为了告诉各位读者，Apache Camel支持动态加载/卸载编排的路由
         * 这很重要，因为后续设计的Broker需要依赖这种能力
         * ==========================
         * */

        // 通用没有具体业务意义的代码，只是为了保证主线程不退出
        synchronized (HelloWorldRouter.class) {
            HelloWorldRouter.class.wait();
        }
    }
}
