package com.hull.spring.delegate.gupao;

/**
 * Created by Administrator on 2017/5/14.
 */
public class TargetB implements IDelegate{
    @Override
    public void doing() {
        System.out.println("B is doing!");
    }
}
