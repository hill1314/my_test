package com.hull.test.designPatterns.createPatterns.p1_FactoryMethod;

import com.hull.test.designPatterns.createPatterns.p1_FactoryMethod.MailSender;
import com.hull.test.designPatterns.createPatterns.p1_FactoryMethod.Sender;
import com.hull.test.designPatterns.createPatterns.p1_FactoryMethod.SmsSender;

/**
 * Created by hull on 2017/3/1.
 *
 * 多个工厂方法模式，是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，
 * 则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象。
 */
public class SenderFactory2 {
    public static Sender getMailSender(){
        return new MailSender();
    }

    public static Sender getSmsSender(){
        return new SmsSender();
    }
}
