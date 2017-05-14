package com.hull.spring.delegate.gupao;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Dispatcher implements IDelegate{
    IDelegate delegate;

    Dispatcher(IDelegate d){
        this.delegate = d;
    }

    @Override
    public void doing() {
        delegate.doing();
    }
}
