package com.gupao.homework.designPatterns.d4_delegate;

/**
 * Created by Administrator on 2017/5/21.
 */
public class WorkerA implements IWorker{
    @Override
    public void working() {
        System.out.println("I'm A,I'm working!");
    }
}
