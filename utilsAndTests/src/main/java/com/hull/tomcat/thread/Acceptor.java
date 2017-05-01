package com.hull.tomcat.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2017/5/1.
 */
public class Acceptor {
    private AtomicLong count = new AtomicLong(0);
    private volatile boolean block = false;
    private final int REFUSE_LIMIT = 12;

    public void accept(Client client){
        new Thread(new Worker(client,new Handle())).start();
    }

    class Worker implements Runnable{
        Client client;
        Handle handle;

        public Worker(Client client,Handle handle){
            this.client = client;
            this.handle = handle;
        }

        @Override
        public void run() {
            if(count.incrementAndGet() > REFUSE_LIMIT){
                block = true;
            }
            if(block){
                throw  new RuntimeException("拒绝服务>"+client.getClientId());
            }
            try{
                handle.handle(client);
            }finally {
                count.decrementAndGet();
            }

        }
    }
}
