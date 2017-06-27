package com.gupao.homework.designPatterns.d5_strategy;

/**
 * Created by Administrator on 2017/5/21.
 */
public class StrategyTest {
    public static void main(String[] args) {
        China china = new China(new ForceRecover());
        china.recoverTaiwan();
    }
}
