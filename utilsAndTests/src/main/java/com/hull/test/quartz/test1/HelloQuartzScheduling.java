package com.hull.test.quartz.test1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/4.
 */
public class HelloQuartzScheduling {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

//        JobDetail jobDetail = new JobDetail("helloQuartzJob",
//                Scheduler.DEFAULT_GROUP, HelloQuartzJob.class);
//
//        SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger",
//                Scheduler.DEFAULT_GROUP);
//
//        simpleTrigger.setStartTime(new Date(System.currentTimeMillis()));
//        simpleTrigger.setRepeatInterval(5000);
//        simpleTrigger.setRepeatCount(10);

//        scheduler.scheduleJob(jobDetail, simpleTrigger);

        scheduler.start();

    }
}
