package com.hull.test.designPatterns.structurePatterns.p8_proxy.staticProxy;

import com.hull.test.designPatterns.structurePatterns.p8_proxy.IBusiness;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ProxyTest {
    public static void main(String[] args) {
        IBusiness ib = new LogProxy();
        ib.doAction("haha");
    }
}
