package com.gupao.homework.designPatterns.d5_strategy;

/**
 * Created by Administrator on 2017/5/21.
 */
public class ForceRecover implements IRecoverStrategy {
    @Override
    public boolean doRecover(String str) {
        System.out.println("force recover "+str+"!");
        return true;
    }
}
