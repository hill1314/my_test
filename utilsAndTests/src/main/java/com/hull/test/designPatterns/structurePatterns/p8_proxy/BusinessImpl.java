package com.hull.test.designPatterns.structurePatterns.p8_proxy;

import com.hull.test.designPatterns.structurePatterns.p8_proxy.IBusiness;

/**
 * Created by Administrator on 2017/3/21.
 */
public class BusinessImpl implements IBusiness {
    @Override
    public void doAction(String str) {
        System.out.println("do something :"+str +"!!!");
    }
}
