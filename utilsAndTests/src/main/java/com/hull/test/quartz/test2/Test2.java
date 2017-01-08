package com.hull.test.quartz.test2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/1/4.
 */
public class Test2 {
    static final Log log = LogFactory.getLog(Test2.class); // 日志

    public static void main(String args[]) throws Exception {
        log.info("开始启动定时任务 ...");
        ApplicationContext context = new ClassPathXmlApplicationContext("quartz-applicationContext.xml");
        log.info("定时任务启动成功...");
    }
}
