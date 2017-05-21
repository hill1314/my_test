package com.gupao.homework.designPatterns.d5_strategy;

/**
 * Created by Administrator on 2017/5/21.
 */
public class StrategyTest {
    public static void main(String[] args) {
        String exp = "2+8";
        ICalculator cal = new Plus();
        int result = cal.calculate(exp);
        System.out.println(result);
    }
}
