package com.gupao.homework.designPatterns.d7_template;

/**
 * Created by Administrator on 2017/5/21.
 * 旅行 过程
 */
public abstract class Travel {

    public final void haveATravel(){
        //1、去目的地
        goDestination();
        //2、逛景点
        play();
        //3、回家
        goHome();
    }

    protected abstract void play();

    private void goDestination() {
        System.out.println("fly to destination.");
    }

    private void goHome() {
        System.out.println("go back.");
    }

}
