package com.gupao.homework.designPatterns.d4_delegate;

/**
 * Created by Administrator on 2017/5/21.
 *  两个角色：包工头 和 工人
 */
public class Contractor implements IWorker{
    private IWorker worker;

    Contractor(IWorker worker){
        this.worker = worker;
    }

    @Override
    public void working() {
        worker.working();
    }
}
