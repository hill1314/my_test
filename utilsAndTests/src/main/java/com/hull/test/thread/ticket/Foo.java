package com.hull.test.thread.ticket;

/**
 * Created by Administrator on 2017/1/1.
 */
public class Foo {
    private int x =10;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public synchronized int fix(int y){
        if(x>0){
            x = x - y;
            System.out.println("抢到一张！，还有"+x+"张");
        }else{
            System.out.println("没票了！");

        }
        return x;
    }


}
