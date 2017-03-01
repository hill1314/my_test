package com.hull.test.designPatterns.createPatterns.p1_FactoryMethod;

/**
 * Created by hull on 2017/3/1.
 */
public class SenderFactory {
    public static Sender getSender(String type){
        Sender sender ;
        if("mail".equals(type)){
            sender = new MailSender();
        }else if("sms".equals(type)){
            sender = new SmsSender();
        }else{
            sender = null;
        }

        return sender;
    }
}
