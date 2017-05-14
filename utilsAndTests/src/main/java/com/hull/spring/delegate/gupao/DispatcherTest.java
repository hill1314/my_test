package com.hull.spring.delegate.gupao;

/**
 * Created by Administrator on 2017/5/14.
 */
public class DispatcherTest {
    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher(new TargetA());
        //看上去是经理在工作，其实是员工在干
        dispatcher.doing();
    }
}
