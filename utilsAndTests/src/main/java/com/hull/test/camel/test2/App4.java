package com.hull.test.camel.test2;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/1.
 */
public class App4 {
    public static void main(String[] args) throws Exception {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(); // 创建connectionFactory
        connectionFactory.setHostName("localhost");
        connectionFactory.setPassword("1234567890");
        connectionFactory.setPort(9999);
        SimpleRegistry registry = new SimpleRegistry();
        connectionFactory.afterPropertiesSet(); // 必须要调用该方法来初始化connectionFactory
        registry.put("connectionFactory", connectionFactory); //注册connectionFactory
        registry.put("serializer", new StringRedisSerializer()); //注册serializer

        CamelContext context = new DefaultCamelContext(registry);
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                errorHandler(deadLetterChannel("stream:out"));
                from("timer://foo?fixedRate=true&period=1000").
                        setHeader("CamelRedis.Command", constant("PUBLISH")).
                        setHeader("CamelRedis.Channel", constant("testChannel")).
                        setHeader("CamelRedis.Message", constant(new Date().toString())).
                        to("spring-redis://localhost:9999?connectionFactory=#connectionFactory&serializer=#serializer");
            }
        });
        context.setTracing(true);
        context.start();
        Thread.sleep(Integer.MAX_VALUE);
        context.stop();
    }
}
