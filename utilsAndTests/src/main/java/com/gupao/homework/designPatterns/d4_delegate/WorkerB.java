package com.gupao.homework.designPatterns.d4_delegate;

/**
 * Created by Administrator on 2017/5/21.
 */
public class WorkerB implements IWorker{
    @Override
    public void working() {
        System.out.println("I'm B,I'm working!");
    }
}
