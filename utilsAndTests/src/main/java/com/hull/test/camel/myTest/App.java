package com.hull.test.camel.myTest;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by Administrator on 2017/1/1.
 */
public class App {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext(); // 1. 创建 CamelContext.

        MyRouterBuilder routerBuilder = new MyRouterBuilder();  // 2. 为路由配置组件或终端节点.
        context.addRoutes(routerBuilder);// 3. 添加路由到CamelContext

        context.setTracing(true);
        context.start(); // 4. 启动CamelContext.
//        Thread.sleep(Integer.MAX_VALUE);  // 为了保持CamelContext处于工作状态，这里需要sleep主线程
        boolean loop =true;
        while(loop){
            Thread.sleep(25000);
        }
        context.stop(); // 最后停止CamelContext

    }
}
