package com.gupao.lessons.designPatterns.d4_delegate;

/**
 * Created by Administrator on 2017/5/14.
 */
public class TargetA implements IDelegate{
    @Override
    public void doing() {
        System.out.println("A is doing!");
    }
}
