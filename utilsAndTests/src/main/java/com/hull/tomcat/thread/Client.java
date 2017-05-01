package com.hull.tomcat.thread;


import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/5/1.
 */
public class Client {
    private String clientId;

    public Client(String clientId){
        this.clientId = clientId;
    }

    public void callBack(String resp){
        System.out.println("Client 消息返回："+resp);
    }

    public void send(Acceptor acceptor){
        System.out.println("消息发送》"+Thread.currentThread().getName()+" - "+Thread.currentThread().getId());
        acceptor.accept(this);
    }



    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
