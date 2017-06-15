package com.hull.test.thread.pool;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/6/15.
 */
public class MyTask3 implements Callable {

    private List<TcSupLoanInfo> list;

    public MyTask3(List<TcSupLoanInfo> list){
        this.list = list;
    }

    @Override
    public Object call() throws Exception {
        for(TcSupLoanInfo loanInfo : list){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(loanInfo.getDueNum());
        }
        return true;
    }
}
