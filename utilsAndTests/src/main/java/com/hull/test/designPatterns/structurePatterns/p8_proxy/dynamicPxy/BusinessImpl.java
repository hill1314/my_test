package com.hull.test.designPatterns.structurePatterns.p8_proxy.dynamicPxy;

/**
 * Created by Administrator on 2017/3/21.
 */
public class BusinessImpl implements IBusiness{
    @Override
    public void doAction() {
        System.out.println("do something !!!");
    }
}
