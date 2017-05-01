package com.hull.tomcat.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/5/1.
 * 来自：
 * http://www.toutiao.com/a6395874551079125249/
 *
 */
public class Test {

    public static void main(String[] args) throws IOException {
        List<Client> clientList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        final Acceptor acceptor = new Acceptor();

        try{
            for (int i=0;i<15;i++){
                final Client client = new Client("Client"+1);
                executorService.submit((Runnable)()->client.send(acceptor));
            }
        }catch (Exception e){

        }finally {
            System.in.read();   //直到用户输入才会停止主线程
            executorService.shutdown();
        }



    }
}
