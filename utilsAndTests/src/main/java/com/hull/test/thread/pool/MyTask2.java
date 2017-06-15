package com.hull.test.thread.pool;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public class MyTask2 implements Runnable{

    private List<TcSupLoanInfo> list;

    public MyTask2(List<TcSupLoanInfo> list){
        this.list = list;
    }

    @Override
    public void run() {
        for(TcSupLoanInfo loanInfo : list){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(loanInfo.getDueNum());
        }

    }
}
