package com.hull.spring.delegate.gupao;

/**
 * Created by Administrator on 2017/5/14.
 */
public class TargetA implements IDelegate{
    @Override
    public void doing() {
        System.out.println("A is doing!");
    }
}
