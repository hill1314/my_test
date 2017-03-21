package com.hull.test.designPatterns.structurePatterns.p8_proxy.dynamicPxy;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ProxyTest {
    public static void main(String[] args) {
        IBusiness ib = new BusinessImplProxy();
        ib.doAction();
    }
}
