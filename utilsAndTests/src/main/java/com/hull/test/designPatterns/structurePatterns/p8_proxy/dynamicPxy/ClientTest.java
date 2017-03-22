package com.hull.test.designPatterns.structurePatterns.p8_proxy.dynamicPxy;

import com.hull.test.designPatterns.structurePatterns.p8_proxy.BusinessImpl;
import com.hull.test.designPatterns.structurePatterns.p8_proxy.IBusiness;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ClientTest {
    public static void main(String[] args) {
        LogHandler logHandler = new LogHandler();
        IBusiness business = (IBusiness) logHandler.newProxyInstance(new BusinessImpl());
        business.doAction("haha");
    }
}
