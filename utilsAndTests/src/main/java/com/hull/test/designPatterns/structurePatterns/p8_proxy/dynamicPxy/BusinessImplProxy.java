package com.hull.test.designPatterns.structurePatterns.p8_proxy.dynamicPxy;

/**
 * Created by Administrator on 2017/3/21.
 */
public class BusinessImplProxy implements IBusiness{
    private BusinessImpl bi;

    @Override
    public void doAction() {
        if(bi==null){
            bi = new BusinessImpl();
        }
        doBefore();
        bi.doAction();
        doAfter();
    }

    private void doAfter() {
        System.out.println("后置处理！");
    }

    private void doBefore() {
        System.out.println("前置处理！");
    }
}
