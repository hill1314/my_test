package com.hull.test.designPatterns.structurePatterns.p8_proxy.staticProxy;

import com.hull.test.designPatterns.structurePatterns.p8_proxy.BusinessImpl;
import com.hull.test.designPatterns.structurePatterns.p8_proxy.IBusiness;

/**
 * Created by Administrator on 2017/3/21.
 */
public class LogProxy implements IBusiness {
    private BusinessImpl bi;

    public LogProxy(){
        super();
        this.bi = new BusinessImpl();
    }

    @Override
    public void doAction(String str) {
        doBefore();
        bi.doAction(str);
        doAfter();
    }

    private void doAfter() {
        System.out.println("后置处理！");
    }

    private void doBefore() {
        System.out.println("前置处理！");
    }
}
