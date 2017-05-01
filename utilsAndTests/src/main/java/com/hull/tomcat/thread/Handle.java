package com.hull.tomcat.thread;

/**
 * Created by Administrator on 2017/5/1.
 */
public class Handle {
    public void handle(Client client){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.callBack("from server >>"
                +Thread.currentThread().getName()+" - "
                +Thread.currentThread().getId());
    }
}
