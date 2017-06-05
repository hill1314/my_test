package com.hull.test.thread.callable;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/6/5.
 */
public class MyCallable implements Callable{

    @Override
    public Object call() throws Exception {
        return "hello ...";
    }
}
