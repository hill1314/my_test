package com.hull.test.redis;

/**
 * Created by Administrator on 2017/4/5.
 */
public class SequenceThread implements Runnable{
    @Override
    public void run() {
        RedisSequence.getNextValue("subStan");
    }

    public static void main(String[] args) {
        SequenceThread thread = new SequenceThread();
        for(int i =0;i<1000;i++){
            new Thread(thread).start();
        }
    }
}
