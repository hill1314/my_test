package com.hull.test.javabase.jdk1_8NewFeatures;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/22.
 */
public class F3_Clock {
    public static void main(String[] args) {
        //Clock是时区敏感的，可以用来取代 System.currentTimeMillis() 来获取当前的微秒数
        Clock clock = Clock.systemDefaultZone();
        long now = System.currentTimeMillis();
        long millis = clock.millis();
        System.out.println(now);
        System.out.println(millis);
        //当前时刻
        Instant instant = clock.instant();
        Date date = Date.from(instant);   // legacy java.util.Date
        System.out.println(date.toString());

        //Timezones 时区
        System.out.println(ZoneId.getAvailableZoneIds());
// prints all available timezone ids
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        //LocalTime 本地时间
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));  // false
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239

    }
}
