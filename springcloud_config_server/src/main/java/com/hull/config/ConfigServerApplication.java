package com.hull.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author hull
 * @title 测试配置中心
 * @description
 * @date 2017/4/27 17:02
 */
@Configuration
@EnableAutoConfiguration
//@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
