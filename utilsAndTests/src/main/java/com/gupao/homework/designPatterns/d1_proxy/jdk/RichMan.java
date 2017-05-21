package com.gupao.homework.designPatterns.d1_proxy.jdk;

/**
 * Created by Administrator on 2017/5/21.
 */
public class RichMan implements Investor{

    private String name ;

    public RichMan(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void buy(String name) {
        System.out.println("buy "+name +" share");
    }

    @Override
    public void sell(String name) {
        System.out.println("sell "+name +" share");
    }
}
