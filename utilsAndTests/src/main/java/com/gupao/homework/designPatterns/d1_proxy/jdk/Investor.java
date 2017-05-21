package com.gupao.homework.designPatterns.d1_proxy.jdk;

/**
 * Created by Administrator on 2017/5/21.
 * 投资者
 */
public interface Investor {

    public String getName();

    public void buy(String name);

    public void sell(String name);
}
